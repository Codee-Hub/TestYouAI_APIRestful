package github.devhub.testyouai.adapter.out.repository;

import github.devhub.testyouai.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepoitory extends JpaRepository<Question, Long> {
}
