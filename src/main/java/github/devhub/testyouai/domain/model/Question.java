package github.devhub.testyouai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String text;
    private String justification;
    private Boolean isCorrect;
}
