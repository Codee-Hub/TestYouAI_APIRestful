package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByUserAppId(Long id);
}
