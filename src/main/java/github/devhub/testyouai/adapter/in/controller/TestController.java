package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.aplication.service.TestService;
import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.aplication.service.GptService;
import github.devhub.testyouai.domain.model.User;
import github.devhub.testyouai.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@Tag(name = "Test Controller", description = "Geração de questionários com GPT")
public class TestController {

    private final GptService gptService;
    private final TestService testService;

    @GetMapping
    @Operation(summary = "Gera um questionário com base no tema, número de perguntas e nível")
    public Test getTest(
            @Parameter(description = "Tema do questionário") @RequestParam String theme,
            @Parameter(description = "Número de perguntas") @RequestParam int numberOfQuestions,
            @Parameter(description = "Nível de dificuldade") @RequestParam String level,
            @Parameter(description = "Id do usuário (opcional)") @RequestParam(required = false) Long idUser
    ) {
        return gptService.gerarQuestionario(theme, numberOfQuestions, level, idUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um test pelo ID")
    public ResponseEntity<Test> getTestById(@PathVariable @NotNull Long id) {
        Test test = testService.findById(id).get();
        return ResponseEntity.ok(test);
    }
}
