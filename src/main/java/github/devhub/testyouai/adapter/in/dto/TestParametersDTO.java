package github.devhub.testyouai.adapter.in.dto;

public record TestParametersDTO(
        String theme,
        int numberOfQuestions,
        String level,
        String tokenJwt
) {
}
