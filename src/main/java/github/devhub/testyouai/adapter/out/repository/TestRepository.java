package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
