package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
