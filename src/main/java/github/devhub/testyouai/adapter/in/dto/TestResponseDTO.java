package github.devhub.testyouai.adapter.in.dto;

import java.util.List;

public record TestResponseDTO(
        Long id,

        String theme,

        String level,

        Integer numberOfQuestions,

        Boolean wasAnswered,

        UserRequestDTO user,

        List<QuestionResponseDTO> questionList
) {
}
