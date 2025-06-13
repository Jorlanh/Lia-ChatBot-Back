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
        repository.deleteAll();

        List<KnowledgeEntry> entries = Arrays.asList(
            new KnowledgeEntry("start", "Olá! 😊 Sou a LIA (Liga de Inclusão e Acessibilidade), sua assistente virtual. Como posso te ajudar hoje?", 
                Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Direitos e Leis", "Ajuda na Escola"), 
                Arrays.asList("início", "começar", "menu", "voltar")),
            new KnowledgeEntry("Sobre o Projeto", "Este projeto busca promover a autonomia e inclusão no Colégio Estadual Governador Roberto Santos. O que mais gostaria de saber sobre ele?", 
                Arrays.asList("Objetivos", "Justificativa", "Metodologia", "Impacto Esperado", "Voltar ao Início"), 
                Arrays.asList("projeto", "sobre", "trabalho")),
            new KnowledgeEntry("Objetivos", "Nosso objetivo principal é promover a inclusão. Especificamente, queremos: 1) Identificar barreiras no colégio; 2) Apresentar recursos de TA; 3) Capacitar profissionais e familiares.", 
                Arrays.asList("Justificativa", "Metodologia", "Voltar ao Início"), 
                Arrays.asList("objetivo")),
            new KnowledgeEntry("Justificativa", "O projeto se justifica pela necessidade de tornar o conhecimento sobre TA mais acessível, capacitando a comunidade e empoderando pessoas com deficiência e suas redes de apoio.", 
                Arrays.asList("Objetivos", "Impacto Esperado", "Voltar ao Início"), 
                Arrays.asList("por que", "justificativa", "motivo")),
            new KnowledgeEntry("Metodologia", "Usamos a pesquisa-ação, que tem 3 fases: 1) Diagnóstico (com entrevistas e questionários); 2) Intervenção (com oficinas e este chatbot!); 3) Avaliação (para medir o impacto).", 
                Arrays.asList("Objetivos", "Sobre o Projeto", "Voltar ao Início"), 
                Arrays.asList("metodologia", "como foi feito")),
            new KnowledgeEntry("Impacto Esperado", "Esperamos impactar diretamente 100 pessoas e indiretamente mais de 250, reduzindo barreiras na educação, promovendo a autonomia e mudando a percepção social sobre a deficiência.", 
                Arrays.asList("Como posso ajudar?", "Recursos do Projeto", "Voltar ao Início"), 
                Arrays.asList("impacto", "resultados esperados")),
            new KnowledgeEntry("Tecnologia Assistiva", "Tecnologia Assistiva (TA) são recursos e serviços que ajudam pessoas com deficiência a terem mais autonomia. Sobre o que você quer saber mais?", 
                Arrays.asList("Exemplos de Software", "Exemplos de Hardware", "Recursos do Projeto", "Voltar ao Início"), 
                Arrays.asList("ta", "tecnologia assistiva", "assistiva")),
            new KnowledgeEntry("Recursos do Projeto", "No projeto, planejamos usar recursos como Impressora 3D para prototipagem, kits de eletrônica (Arduino) para criar dispositivos, e softwares como NVDA e Communicator 5.", 
                Arrays.asList("Exemplos de Software", "Exemplos de Hardware", "Voltar ao Início"), 
                Arrays.asList("recursos do projeto")),
            new KnowledgeEntry("Exemplos de Software", "Softwares de TA são programas que auxiliam no uso do computador ou celular. Quer ver exemplos para qual necessidade?", 
                Arrays.asList("Deficiência Visual", "Dificuldade de Fala", "Deficiência Auditiva", "Voltar ao Início"), 
                Arrays.asList("software", "programa", "app", "aplicativo")),
            new KnowledgeEntry("Exemplos de Hardware", "Hardware de TA são equipamentos físicos. Exemplos: mouses e teclados adaptados, acionadores que permitem controlar dispositivos com o piscar dos olhos, e impressões 3D para engrossar lápis.", 
                Arrays.asList("Exemplos de Software", "O que é TA?", "Voltar ao Início"), 
                Arrays.asList("hardware", "equipamento", "dispositivo")),
            new KnowledgeEntry("Deficiência Visual", "Para deficiência visual, os principais são os Leitores de Tela, como o NVDA (gratuito) e o Jaws. Eles leem em voz alta tudo o que aparece na tela.", 
                Arrays.asList("Dificuldade de Fala", "Deficiência Auditiva", "Voltar ao Início"), 
                Arrays.asList("visual", "cego", "baixa visão")),
            new KnowledgeEntry("Dificuldade de Fala", "Para quem tem dificuldade na fala, existem os apps de Comunicação Alternativa (CAA), como o Livox e o Communicator 5. Eles usam símbolos e vozes sintetizadas para formar frases.", 
                Arrays.asList("Deficiência Visual", "Deficiência Auditiva", "Voltar ao Início"), 
                Arrays.asList("fala", "mudo", "dificuldade de fala")),
            new KnowledgeEntry("Deficiência Auditiva", "Para a comunidade surda, aplicativos como o Hand Talk são incríveis! Eles traduzem texto e voz para Libras (Língua Brasileira de Sinais) em tempo real.", 
                Arrays.asList("Deficiência Visual", "Dificuldade de Fala", "Voltar ao Início"), 
                Arrays.asList("auditiva", "surdo")),
            new KnowledgeEntry("Direitos e Leis", "A Lei Brasileira de Inclusão (nº 13.146/2015) é a principal. Ela garante direito à educação em sistema inclusivo e acessibilidade. Quer saber mais sobre algum ponto?", 
                Arrays.asList("Como pedir material?", "Denunciar Bullying", "Voltar ao Início"), 
                Arrays.asList("direito", "leis", "lbi")),
            new KnowledgeEntry("Como pedir material?", "Para solicitar material adaptado (em Braille, ampliado ou digital), fale com o professor da sua turma ou vá diretamente à Sala de Recursos Multifuncionais. O prazo médio é de 15 dias.", 
                Arrays.asList("Quais meus direitos?", "Contato da Coordenação", "Voltar ao Início"), 
                Arrays.asList("material", "adaptado", "braille", "livro")),
            new KnowledgeEntry("Denunciar Bullying", "Bullying é inaceitável. Você pode e deve denunciar, de forma anônima se preferir. Procure a Orientação Educacional na sala 103 ou relate o ocorrido para qualquer professor ou coordenador de sua confiança. Para reportar bullying escolar, pode-se utilizar o Disque 100, um serviço de atendimento gratuito e anônimo que funciona 24 horas por dia. Além disso, o Conselho Tutelar é um canal de atendimento para crianças e adolescentes e o Ministério Público pode também ser procurado.", 
                Arrays.asList("Problema de Acessibilidade", "Direitos e Leis", "Voltar ao Início"), 
                Arrays.asList("bullying", "discriminação", "preconceito", "denunciar")),
            new KnowledgeEntry("Ajuda na Escola", "Vamos lá. Escolha qual das opções tem interesse em saber.", 
                Arrays.asList("Contato da Coordenação", "Eventos Acessíveis", "Parcerias do Projeto", "Reportar um Problema", "Como posso ajudar?", "Voltar ao Início"), 
                Arrays.asList("ajuda", "apoio", "escola")),
            new KnowledgeEntry("Contato da Coordenação", "Para questões de acessibilidade e apoio pedagógico, procure a Coordenação Pedagógica no número +55 71 3333-3333 ou a equipe da Sala de Recursos Multifuncionais no Bloco B.", 
                Arrays.asList("Como pedir material?", "Eventos Acessíveis", "Voltar ao Início"), 
                Arrays.asList("contato", "falar com", "coordenação", "coordenador")),
            new KnowledgeEntry("Como posso ajudar?", "Fiquei feliz em saber que o(a) senhor(a) tem interesse em nos ajudar. Para isso, entre em contato com a Coordenação Pedagógica pelo telefone +55 71 3333-3333 ou envie uma mensagem para escola@example.com para suporte personalizado.", 
                Arrays.asList("Eventos Acessíveis", "Contato da Coordenação", "Voltar ao Início"), 
                Arrays.asList("contato", "ajuda", "coordenação", "escola")),
            new KnowledgeEntry("Eventos Acessíveis", "A escola se esforça para que todos os eventos sejam acessíveis! Sempre haverá intérpretes de Libras em apresentações e espaços reservados para cadeirantes.", 
                Arrays.asList("Denunciar Bullying", "Contato da Coordenação", "Voltar ao Início"), 
                Arrays.asList("evento", "festa", "apresentação")),
            new KnowledgeEntry("Reportar um Problema", "Obrigado por nos ajudar a melhorar! Por favor, descreva o problema de acessibilidade que você encontrou (ex: 'rampa quebrada no pátio', 'luz piscando na sala 5'). Sua mensagem será enviada para escola@example.com.", 
                Arrays.asList("Denunciar Bullying", "Voltar ao Início"), 
                Arrays.asList("reportar", "relatar", "problema", "problema de acessibilidade")),
            new KnowledgeEntry("Parcerias do Projeto", "A parceira principal do projeto é a LIA (Liga de Inclusão e Acessibilidade) e está em parceria com a escola Colégio Estadual Governador Roberto Santos.", 
                Arrays.asList("Recursos do Projeto", "Voltar ao Início"), 
                Arrays.asList("parcerias", "parceiro")),
            new KnowledgeEntry("cr7", "Embora Cristiano Ronaldo (CR7) seja conhecido por seu talento no futebol, ele também apoia causas de inclusão! Por exemplo, ele já participou de campanhas que promovem acessibilidade no esporte para pessoas com deficiência. Para mais detalhes sobre inclusão e acessibilidade, use a IA do Gemini integrada, que responde a qualquer pergunta relacionada a esses temas.", 
                Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Voltar ao Início"), 
                Arrays.asList("cr7")),
            new KnowledgeEntry("default", "Não entendi sua pergunta. Que tal tentar uma das opções abaixo?", 
                Arrays.asList("Sobre o Projeto", "Tecnologia Assistiva", "Direitos e Leis", "Ajuda na Escola"), 
                Arrays.asList("default_key"))
        );

        repository.saveAll(entries);
        System.out.println(">>> Base de conhecimento carregada com " + entries.size() + " entradas.");
    }
}