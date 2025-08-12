package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.domain.model.UserApp;
import github.devhub.testyouai.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //findByEmail

    public Optional<UserApp> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserApp> findAll() {
        return Collections.unmodifiableList(userRepository.findAll());
    }

    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, UserApp userAppUpdated) {
        userAppUpdated.setId(id);
        userRepository.save(userAppUpdated);
    }

    public UserApp saveUser(UserApp userApp) {
        userApp.setPassword( passwordEncoder.encode(userApp.getPassword()));
        return userRepository.save(userApp);
    }

    public UserApp findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

}
