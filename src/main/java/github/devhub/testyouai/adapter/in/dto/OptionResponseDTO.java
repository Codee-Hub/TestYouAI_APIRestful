package github.devhub.testyouai.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import github.devhub.testyouai.domain.model.Question;
import jakarta.persistence.*;

public record OptionResponseDTO(
         Long id,

         String textOption,

         String justification,

         Boolean wasSelect,

         Boolean isCorrect
) {
}
