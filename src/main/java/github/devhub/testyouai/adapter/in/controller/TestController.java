package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.aplication.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final GptService gptService;

    @GetMapping
    public Test getTest(
            @RequestParam String theme,
            @RequestParam int numberOfQuestions,
            @RequestParam String level
    ) {
        return gptService.gerarQuestionario(theme, numberOfQuestions, level);
    }
}
