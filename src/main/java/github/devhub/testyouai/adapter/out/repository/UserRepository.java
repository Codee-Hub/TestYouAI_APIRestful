package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
