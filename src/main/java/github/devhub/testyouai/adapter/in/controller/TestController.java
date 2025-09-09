package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.adapter.in.dto.TestParametersDTO;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

    @PostMapping
    @Operation(summary = "Gera um questionário com base no tema, número de perguntas e nível")
    public Test getTest(@RequestBody @Valid TestParametersDTO parameters) {
        return gptService.gerarQuestionario(parameters.theme(), parameters.numberOfQuestions(), parameters.level(), parameters.userId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um test pelo ID")
    public ResponseEntity<TestResponseDTO> getTestById(@PathVariable @NotNull Long id) {
        Test test = testService.findById(id).orElse(null);
        TestResponseDTO testResponseBasicDTO = testMapper.toDTO(test);
        return ResponseEntity.ok(testResponseBasicDTO);
    }

    @GetMapping("/users")
    @Operation(summary = "Busca um test pelo ID do usuario")
    public ResponseEntity<List<TestResponseBasicDTO>> getTestByUserId(JwtAuthenticationToken tokenJwt) {
        List<TestResponseBasicDTO> testResponseBasicDTOList = testService.findByUserId(Long.valueOf(tokenJwt.getName()))
                .stream()
                .map(testBasicMapper::toDTO)
                .toList();

        return ResponseEntity.ok(testResponseBasicDTOList);
    }
}
