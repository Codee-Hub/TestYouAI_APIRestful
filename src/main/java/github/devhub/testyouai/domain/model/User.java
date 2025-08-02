package github.devhub.testyouai.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nome;

    @Column(name = "phone_number")
    private String phoneNumber;
/*
    @OneToMany(mappedBy = "id")
    private List<Test> testList;*/
}
