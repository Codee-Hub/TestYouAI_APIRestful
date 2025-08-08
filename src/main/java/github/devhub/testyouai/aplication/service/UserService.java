package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.domain.model.User;
import github.devhub.testyouai.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //findByEmail

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(userRepository.findAll());
    }

    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User userUpdated) {
        userUpdated.setId(id);
        userRepository.save(userUpdated);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
