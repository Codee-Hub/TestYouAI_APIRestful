package github.devhub.testyouai.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String theme;
    private String level;

    @Column(name = "number_of_questions")
    private Integer numberOfQuestions;

    @Column(name = "question_list")
    @OneToMany(mappedBy = "id")
    private List<Question> questionList;

    @JsonIgnore
    @ManyToOne
    private User user;
}
