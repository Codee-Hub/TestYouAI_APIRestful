package github.devhub.testyouai.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nome;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "id")
    private List<Test> testList;
}
