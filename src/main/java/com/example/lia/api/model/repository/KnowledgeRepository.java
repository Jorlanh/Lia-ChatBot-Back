package com.example.lia.api.model.repository;

import com.example.lia.api.model.entity.KnowledgeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<KnowledgeEntry, String> {
    // Spring Data JPA cria as implementações de busca automaticamente!
}