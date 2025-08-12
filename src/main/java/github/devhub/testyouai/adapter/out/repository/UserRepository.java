package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    public Optional<UserApp> findByEmail(String email);
}
