package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
