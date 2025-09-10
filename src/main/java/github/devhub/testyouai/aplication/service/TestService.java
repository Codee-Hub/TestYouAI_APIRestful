package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.adapter.out.repository.OptionRepository;
import github.devhub.testyouai.adapter.out.repository.QuestionRepository;
import github.devhub.testyouai.adapter.out.repository.TestRepository;
import github.devhub.testyouai.domain.model.Option;
import github.devhub.testyouai.domain.model.Question;
import github.devhub.testyouai.domain.model.Test;
import github.devhub.testyouai.domain.model.UserApp;
import github.devhub.testyouai.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    private final QuestionRepository questionRepository;

    private final OptionRepository optionRepository;


    public Optional<Test> findById(Long id){
        return testRepository.findById(id);
    }

    public List<Test> findByUserId(Long userId){
        return testRepository.findByUserAppId(userId);
    }

    public Test save(Test test){
        testRepository.save(test);
        for(Question questao : test.getQuestionList()){
            questao.setTest(test);
            questionRepository.save(questao);
            for(Option option : questao.getOptionList()){
                option.setQuestion(questao);
                optionRepository.save(option);
            }
        }

        return test;
    }

    public Test update(Long id ,Test newTest){
        Test test = testRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        newTest.setId(test.getId());
        return save(newTest);
    }
}
