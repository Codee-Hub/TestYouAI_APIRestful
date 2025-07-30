package github.devhub.testyouai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String theme;
    private String level;
    private Integer numberOfQuestions;
    private List<Question> questionList;
}
