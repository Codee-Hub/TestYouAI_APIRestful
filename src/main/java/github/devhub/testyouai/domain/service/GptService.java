package github.devhub.testyouai.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devhub.testyouai.domain.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

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

            Formato de retorno:
            {
              "dificuldade": "string",
              "numeroDePerguntas": int,
              "perguntasList": [
                {
                  "text": "Texto da pergunta",
                  "justification": "Justificativa da resposta",
                  "isCorrect": true
                }
              ]
            }

            Apenas retorne o JSON. Não adicione explicações nem formatações extras.
            """, tema, numeroDePerguntas, dificuldade);

        try {
            var response = chatModel.call(new Prompt(promptText, OpenAiChatOptions.builder()
                    .model("gpt-4o")
                    .temperature(0.7)
                    .build()));

            String json = response.getResult().getOutput().toString();

            return objectMapper.readValue(json, Test.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar questionário com GPT: " + e.getMessage(), e);
        }
    }
}
