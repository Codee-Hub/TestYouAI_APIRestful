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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_question")
    private String textQuestion;

    @OneToMany(mappedBy = "id")
    private List<Option> optionList;

    @JsonIgnore
    @ManyToOne
    private Test test;
}
