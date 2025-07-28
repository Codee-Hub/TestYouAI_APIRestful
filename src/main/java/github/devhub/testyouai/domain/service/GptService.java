package github.devhub.testyouai.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.devhub.testyouai.domain.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

//    // Retrieve API key from a secure source or environment variable
//    String apiKey = System.getenv("OPENAI_API_KEY");

    private final ChatModel chatModel;

    @Bean
    public CommandLineRunner runner(ChatClient.Builder builder) {
        return args -> {
            ChatResponse response = chatModel.call(
                    new Prompt(
                            "Gere adjetivos maravilhosos e criativos e poeticos para minha mulher medica Pamela",
                            OpenAiChatOptions.builder()
                                    .temperature(1.0)
                                    .build()));
            System.out.println(response);
        };
    }
}
