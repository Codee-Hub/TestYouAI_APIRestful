package github.devhub.testyouai.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devhub.testyouai.domain.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GptService {

//    List<Test> lista = new ArrayList<>();

//    @Bean
//    public CommandLineRunner run(GptService gptService) {
//        return args -> {
//            Test questionario = gptService.gerarQuestionario("Spring Boot", 10, "médio");
//            lista.add(questionario);
//            System.out.println(questionario);
//        };
//    }


    private final ChatModel chatModel;
    private final ObjectMapper objectMapper;

    /**
     * Gera um questionário com base no tema, número de perguntas e dificuldade.
     *
     * @param tema Tema do questionário (ex: "Java", "Matemática", etc)
     * @param numeroDePerguntas Quantidade de perguntas
     * @param dificuldade Nível de dificuldade (ex: "fácil", "médio", "difícil")
     * @return objeto Test com as perguntas
     */
    public Test gerarQuestionario(String tema, int numeroDePerguntas, String dificuldade) {
        String promptText = String.format("""
    Gere um questionário em JSON com as seguintes características:
    - Tema: %s
    - Número de perguntas: %d
    - Dificuldade: %s

    Estrutura do JSON:
    {
      "theme":"string",
      "level": "string",
      "numberOfQuestions": int,
      "questionList": [
        {
          "textQuestion": "string",
          "optionList": [
            {
              "textOption": "Texto da alternativa",
              "justification": "Justificativa da resposta",
              "isCorrect": true
            },
            {
              "text": "...",
              "justification": "...",
              "isCorrect": false
            },
            ...
          ]
        }
      ]
    }

    - Cada pergunta deve ter exatamente 5 alternativas (answers).
    - Apenas uma alternativa deve ter "isCorrect": true.
    - O JSON deve ser válido e compatível com o padrão acima.
    - Retorne **somente** o JSON, sem explicações ou formatação adicional.
    """, tema, numeroDePerguntas, dificuldade);


        try {
            var response = chatModel.call(new Prompt(promptText, OpenAiChatOptions.builder()
                    .model("gpt-4o")
                    .temperature(0.7)
                    .build()));

            String rawText  = response.getResult().getOutput().getText();
            String json = extractJson(rawText); // remove blocos de código

            return objectMapper.readValue(json, Test.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar questionário com GPT: " + e.getMessage(), e);
        }
    }

    private String extractJson(String text) {
        if (text.startsWith("```")) {
            return text.replaceAll("(?s)```(?:json)?", "").trim();
        }
        return text.trim();
    }

}
