package github.devhub.testyouai.adapter.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TestParametersDTO(
        @NotBlank
        String theme,
        @Min(1)
        int numberOfQuestions,
        @NotBlank
        String level,
        String tokenJwt
) {
}
