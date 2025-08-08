package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.aplication.service.GptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@Tag(name = "Test Controller", description = "Geração de questionários com GPT")
public class TestController {

    private final GptService gptService;

    @GetMapping

    @Operation(summary = "Gera um questionário com base no tema, número de perguntas e nível")
    public Test getTest(
            @Parameter(description = "Tema do questionário") @RequestParam String theme,
            @Parameter(description = "Número de perguntas") @RequestParam int numberOfQuestions,
            @Parameter(description = "Nível de dificuldade") @RequestParam String level
    ) {
        return gptService.gerarQuestionario(theme, numberOfQuestions, level);
    }
}
