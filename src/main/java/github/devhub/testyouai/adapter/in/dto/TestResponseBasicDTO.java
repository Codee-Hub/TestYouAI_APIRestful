package github.devhub.testyouai.adapter.in.dto;

public record TestResponseBasicDTO(
         Long id,

         String theme,

         String level,

         Integer numberOfQuestions,

         Boolean wasAnswered,

         UserRequestDTO user
) {
}
