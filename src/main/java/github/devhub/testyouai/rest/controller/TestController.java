package github.devhub.testyouai.rest.controller;

import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.domain.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final GptService gptService;

    @GetMapping
    public Test getTest(
            @RequestParam String tema,
            @RequestParam int numeroDePerguntas,
            @RequestParam String dificuldade
    ) {
        return gptService.gerarQuestionario(tema, numeroDePerguntas, dificuldade);
    }
}
