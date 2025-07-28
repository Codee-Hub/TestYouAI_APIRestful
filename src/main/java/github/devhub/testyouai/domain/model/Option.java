package github.devhub.testyouai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private String textOption;
    private String justification;
    private Boolean isCorrect;
}
