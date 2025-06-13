package com.example.lia.api.controller;

import com.example.lia.api.dto.ChatRequest;
import com.example.lia.api.dto.ChatResponse;
import com.example.lia.api.model.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permite acesso de qualquer origem (bom para desenvolvimento)
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatService.getResponse(request.getMessage());
    }
}