package com.example.lia.api.dto;

import lombok.Data; // Lombok gera getters e setters

@Data 
public class ChatRequest { 
    private String message; 
}