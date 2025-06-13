package com.example.lia.api.model.service;

import com.example.lia.api.dto.ChatResponse;
import com.example.lia.api.model.entity.KnowledgeEntry;
import com.example.lia.api.model.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private GeminiService geminiService;

    public ChatResponse getResponse(String userInput) {
        String lowerInput = userInput.toLowerCase();

        // Verifica se é uma ofensa ou xingamento
        if (containsOffensiveLanguage(lowerInput)) {
            return new ChatResponse("Desculpe, não entendi sua pergunta ou ela contém linguagem inadequada. Por favor, tente novamente com um tom respeitoso.", Arrays.asList(), true);
        }

        // Tratamento especial para "cr7"
        if (lowerInput.contains("cr7")) {
            String cr7Response = "Embora Cristiano Ronaldo (CR7) seja conhecido por seu talento no futebol, ele também apoia causas de inclusão! Por exemplo, ele já participou de campanhas que promovem acessibilidade no esporte para pessoas com deficiência. Para mais detalhes sobre inclusão e acessibilidade, use a IA do Gemini integrada, que responde a qualquer pergunta relacionada a esses temas.";
            return new ChatResponse(cr7Response, Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Voltar ao Início"), true);
        }

        // Procura a entrada exata baseada na sugestão clicada ou palavra-chave
        Optional<KnowledgeEntry> entry = knowledgeRepository.findById(userInput); // Tenta encontrar pela chave exata primeiro
        if (!entry.isPresent()) {
            entry = knowledgeRepository.findAll().stream()
                .filter(e -> e.getRelatedKeywords().stream().anyMatch(lowerInput::contains))
                .findFirst();
        }

        // Se encontrar uma correspondência, retorna a resposta do banco
        if (entry.isPresent()) {
            KnowledgeEntry foundEntry = entry.get();
            return new ChatResponse(foundEntry.getResponse(), foundEntry.getSuggestions(), true);
        }

        // Usa a API do Gemini para qualquer pergunta digitada, garantindo referência a inclusão e acessibilidade
        String geminiResponse = geminiService.generateContent(
            "Aja como a LIA, uma assistente virtual amigável de um projeto de inclusão escolar. Responda à pergunta: \"" + userInput + 
            "\" fazendo sempre referência a inclusão e acessibilidade, mesmo que a pergunta seja sobre outro tema. Mencione que uso a IA do Gemini integrada para responder qualquer mensagem com foco em inclusão e acessibilidade."
        );
        return new ChatResponse(geminiResponse, Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Voltar ao Início"), true);
    }

    private boolean containsOffensiveLanguage(String input) {
        String[] offensiveWords = {"idiota", "burro", "imbecil", "lixo", "foda-se", "caralho"};
        return Arrays.stream(offensiveWords).anyMatch(input::contains);
    }
}