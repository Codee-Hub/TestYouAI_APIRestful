package github.devhub.testyouai.aplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devhub.testyouai.adapter.out.repository.OptionRepository;
import github.devhub.testyouai.adapter.out.repository.QuestionRepository;
import github.devhub.testyouai.adapter.out.repository.TestRepository;
import github.devhub.testyouai.adapter.out.repository.UserRepository;
import github.devhub.testyouai.domain.model.Option;
import github.devhub.testyouai.domain.model.Question;
import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.domain.model.UserApp;
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
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    /**
     * Gera um questionário com base no tema, número de perguntas e dificuldade.
     *
     * @param tema Tema do questionário (ex: "Java", "Matemática", etc)
     * @param numeroDePerguntas Quantidade de perguntas
     * @param dificuldade Nível de dificuldade (ex: "fácil", "médio", "difícil")
     * @return objeto Test com as perguntas
     */
    public Test gerarQuestionario(String tema, int numeroDePerguntas, String dificuldade, Long idUser) {
        String promptText = String.format("""
            Você é um gerador de questionários. Gere um JSON com o seguinte formato abaixo, preenchendo os dados com base nos parâmetros:
            
            Tema: %s  
            Número de perguntas: %d  
            Nível de dificuldade: %s
            
            Apenas retorne o JSON sem nenhuma explicação ou comentário.
            
            Formato esperado:
            {
              "theme":"string",
              "level": "string",
              "numberOfQuestions": int,
              "wasAnswered": false,
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
                      "textOption": "...",
                      "justification": "...",
                      "isCorrect": false
                    }
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
            assert rawText != null;
            String json = extractJson(rawText); // remove blocos de código

            Test test = objectMapper.readValue(json, Test.class);

            if(idUser == null)
                return test;

            if(userRepository.existsById(idUser)){
                UserApp userApp = userRepository.findById(idUser).get();
                userApp.getTestList().add(test);
                test.setUserApp(userApp);
                testRepository.save(test);
                userRepository.save(userApp);
                for(Question questao : test.getQuestionList()){
                    questionRepository.save(questao);
                    for(Option option : questao.getOptionList()){
                        optionRepository.save(option);
                    }
                }
            }
             return test;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar questionário com GPT: " + e.getMessage(), e);
        }
    }

    private String extractJson(String text) {
        // Remove blocos markdown como ```json
        text = text.replaceAll("(?s)```(?:json)?", "").trim();

        // Tenta extrair o JSON entre as primeiras { e a última }
        int start = text.indexOf('{');
        int end = text.lastIndexOf('}');
        if (start != -1 && end != -1 && end > start) {
            return text.substring(start, end + 1);
        }

        return text; // fallback
    }


}
