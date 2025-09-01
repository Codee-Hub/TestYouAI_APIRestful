package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.adapter.in.dto.TestResponseBasicDTO;
import github.devhub.testyouai.adapter.in.dto.TestResponseDTO;
import github.devhub.testyouai.adapter.in.mapper.TestBasicMapper;
import github.devhub.testyouai.adapter.in.mapper.TestMapper;
import github.devhub.testyouai.aplication.service.TestService;
import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.aplication.service.GptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
@Tag(name = "Test Controller", description = "Geração de questionários com GPT")
public class TestController {

    private final GptService gptService;
    private final TestService testService;
    private final TestMapper testMapper;
    private final TestBasicMapper testBasicMapper;

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
    public ResponseEntity<TestResponseDTO> getTestById(@PathVariable @NotNull Long id) {
        Test test = testService.findById(id).orElse(null);
        TestResponseDTO testResponseBasicDTO = testMapper.toDTO(test);
        return ResponseEntity.ok(testResponseBasicDTO);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Busca um test pelo ID do usuario")
    public ResponseEntity<List<TestResponseBasicDTO>> getTestByUserId(@PathVariable @NotNull Long id) {
        List<TestResponseBasicDTO> testResponseBasicDTOList = testService.findByUserId(id)
                .stream()
                .map(testBasicMapper::toDTO)
                .toList();

        return ResponseEntity.ok(testResponseBasicDTOList);
    }
}
