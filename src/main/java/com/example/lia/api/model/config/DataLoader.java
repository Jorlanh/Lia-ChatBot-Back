package com.example.lia.api.model.config;

import com.example.lia.api.model.entity.KnowledgeEntry;
import com.example.lia.api.model.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired 
    private KnowledgeRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Apaga todos os dados antigos para garantir um início limpo a cada execução
        repository.deleteAll();
        
        // Lista de conhecimento inicial e correta
        List<KnowledgeEntry> entries = Arrays.asList(
            new KnowledgeEntry("start", "Olá! Sou a LIA, a sua assistente virtual. Como posso ajudar hoje?", Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Direitos e Leis"), Arrays.asList("start", "início", "voltar")),
            new KnowledgeEntry("projeto", "Este projeto procura promover a autonomia e inclusão. O que mais gostaria de saber?", Arrays.asList("Objetivos", "Justificativa", "Metodologia"), Arrays.asList("projeto", "sobre")),
            new KnowledgeEntry("objetivos", "O nosso objetivo é: 1) Identificar barreiras; 2) Apresentar recursos de TA; 3) Capacitar a comunidade.", Arrays.asList("Justificativa", "Metodologia", "Voltar ao Início"), Arrays.asList("objetivo")),
            new KnowledgeEntry("ta", "Tecnologia Assistiva (TA) são recursos e serviços que ajudam pessoas com deficiência. Sobre o que quer saber mais?", Arrays.asList("Exemplos de Software", "Exemplos de Hardware", "Voltar ao Início"), Arrays.asList("ta", "tecnologia assistiva", "assistiva")),
            new KnowledgeEntry("default", "Não entendi a sua pergunta. Pode tentar uma das opções abaixo?", Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Direitos e Leis"), Arrays.asList("default_key"))
            // Adicione aqui todas as outras entradas da sua base de conhecimento...
        );
        
        repository.saveAll(entries);
        System.out.println(">>> Base de conhecimento carregada com " + entries.size() + " entradas.");
    }
}
