package com.example.lia.api.model.service;

import com.example.lia.api.dto.ChatResponse;
import com.example.lia.api.model.entity.KnowledgeEntry;
import com.example.lia.api.model.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired private KnowledgeRepository knowledgeRepository;
    @Autowired private GeminiService geminiService;

    public ChatResponse getResponse(String userInput) {
        String lowerInput = userInput.toLowerCase();

        // Procura a entrada no banco de dados cuja palavra-chave está contida na entrada do usuário
       Optional<KnowledgeEntry> entry = knowledgeRepository.findAll().stream()
                .filter(e -> e.getRelatedKeywords().stream().anyMatch(lowerInput::contains))
                .findFirst();

        // Se encontrar uma correspondência, retorna a resposta do banco
        if (entry.isPresent()) {
            KnowledgeEntry foundEntry = entry.get();
            return new ChatResponse(foundEntry.getResponse(), foundEntry.getSuggestions());
        }

        String geminiResponse = geminiService.generateContent(userInput);
            return new ChatResponse(geminiResponse, Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Voltar ao Início"));
    }
}