package github.devhub.testyouai.adapter.in.dto;

public record TestRequestBasicDTO(
         Long id,

         String theme,

         String level,

         Integer numberOfQuestions,

         Boolean wasAnswered,

         Long idUser
) {
}
