package com.example.lia.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data 
@AllArgsConstructor 
public class ChatResponse { 
    private String response; 
    private List<String> suggestions; 
}
