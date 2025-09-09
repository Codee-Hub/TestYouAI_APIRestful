package github.devhub.testyouai.adapter.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public record TestParametersDTO(
        @NotBlank
        String theme,
        @Min(1)
        int numberOfQuestions,
        @NotBlank
        String level,
        Long userId
) {
}
