package github.devhub.testyouai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String email;
    private String password;
    private String nome;
    private String phoneNumber;

    private List<Test> testList;
}
