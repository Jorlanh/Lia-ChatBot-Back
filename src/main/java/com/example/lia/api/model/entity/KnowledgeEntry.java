package com.example.lia.api.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Column;
import java.util.List;
import lombok.Data; // Lombok para reduzir boilerplate
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data // Gera getters, setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeEntry {

    @Id
    private String entryKey; // A palavra-chave principal (ex: "direitos")

    @Column(length = 1024) // Permite textos mais longos para a resposta
    private String response;

    @ElementCollection(fetch = FetchType.EAGER) // Cria uma tabela separada para as sugestões relacionadas a esta entrada
    private List<String> suggestions;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> relatedKeywords; // Palavras-chave associadas
}