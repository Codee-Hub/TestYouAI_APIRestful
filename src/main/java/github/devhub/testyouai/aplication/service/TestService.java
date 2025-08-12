package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.adapter.out.repository.TestRepository;
import github.devhub.testyouai.domain.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public Optional<Test> findById(Long id){
        return testRepository.findById(id);
    }

    public Optional<Test> findByUserId(Long userId){
        return testRepository.findByUserId(userId);
    }
}
