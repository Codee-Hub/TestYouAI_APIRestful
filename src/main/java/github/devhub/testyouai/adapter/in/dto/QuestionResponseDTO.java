package github.devhub.testyouai.adapter.in.dto;

import github.devhub.testyouai.domain.model.Option;
import jakarta.persistence.*;

import java.util.List;

public record QuestionResponseDTO(
        Long id,

        String textQuestion,

        List<OptionResponseDTO> optionList
) {
}
