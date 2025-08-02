package github.devhub.testyouai.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text_option")
    private String textOption;

    @Column(name = "Justification")
    private String justification;

    @Column(name = "was_select")
    private Boolean wasSelect = false;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne
    private Question question;
}
