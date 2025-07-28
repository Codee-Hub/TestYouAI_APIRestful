package github.devhub.testyouai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String dificuldade;
    private Integer numeroDePerguntas;
    private List<Pergunta> perguntasList;
}
