package com.example.lia.api.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GeminiService {

    private final String apiKey;
    private final String apiUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Construtor que recebe a chave da API a partir das configurações da aplicação.
     * Isso evita deixar a chave diretamente no código.
     * @param apiKey A chave da API do Gemini, injetada pelo Spring.
     */
    public GeminiService(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + this.apiKey;
    }

    public String generateContent(String userInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody;
        try {
            // O prompt foi mantido como no seu código original
            String prompt = "Aja como a LIA, uma assistente virtual amigável de um projeto de inclusão escolar. Seja conciso e útil. Responda à pergunta: \"" + userInput + "\"";

            ObjectNode part = objectMapper.createObjectNode();
            part.put("text", prompt);

            ObjectNode content = objectMapper.createObjectNode();
            content.set("parts", objectMapper.createArrayNode().add(part));

            ObjectNode root = objectMapper.createObjectNode();
            root.set("contents", objectMapper.createArrayNode().add(content));

            requestBody = objectMapper.writeValueAsString(root);

        } catch (IOException e) {
            System.err.println("### ERRO AO CONSTRUIR O JSON PARA A API ###");
            e.printStackTrace();
            return "Erro interno ao preparar a pergunta para a IA.";
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        try {
            String jsonResponse = restTemplate.postForObject(apiUrl, request, String.class);
            return extractTextFromResponse(jsonResponse);

        } catch (HttpClientErrorException e) {
            System.err.println("### ERRO NA RESPOSTA DA API GEMINI ###");
            System.err.println("Status Code: " + e.getStatusCode());
            System.err.println("Resposta do Erro: " + e.getResponseBodyAsString());
            return "Ocorreu um erro ao comunicar com a IA. Verifique a consola do backend.";
        } catch (Exception e) {
            System.err.println("### ERRO INESPERADO AO CHAMAR A API ###");
            e.printStackTrace();
            return "Desculpe, não consegui pensar numa resposta agora. Pode tentar reformular?";
        }
    }

    private String extractTextFromResponse(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            if (root.has("error")) {
                String errorMessage = root.get("error").get("message").asText();
                System.err.println("Erro funcional retornado pela API: " + errorMessage);
                return "A API retornou um erro: " + errorMessage;
            }
            JsonNode textNode = root.at("/candidates/0/content/parts/0/text");
            return textNode.isMissingNode() ? "Não foi possível gerar uma resposta coerente." : textNode.asText();
        } catch (IOException e) {
            System.err.println("Erro ao analisar o JSON da resposta da IA: " + e.getMessage());
            return "Erro ao processar a resposta da IA.";
        }
    }
}