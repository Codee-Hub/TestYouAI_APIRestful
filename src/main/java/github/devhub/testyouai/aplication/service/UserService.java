package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.adapter.out.repository.RoleRepository;
import github.devhub.testyouai.domain.model.Role;
import github.devhub.testyouai.domain.model.UserApp;
import github.devhub.testyouai.adapter.out.repository.UserRepository;
import github.devhub.testyouai.exception.ForbiddenException;
import github.devhub.testyouai.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

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

    public void updateUser(Long id, UserApp userAppUpdated, JwtAuthenticationToken token) {

        UserApp user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));


        boolean isAdmin = userRepository.findById(Long.valueOf(token.getName())).get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name().toLowerCase()));

        if (isAdmin || Objects.equals(user.getId(), Long.valueOf(token.getName()))) {
            userAppUpdated.setId(id);
            userAppUpdated.setPassword( passwordEncoder.encode(userAppUpdated.getPassword()));
            userRepository.save(userAppUpdated);
        } else {
            throw new ForbiddenException("O usuário com ID " + Long.valueOf(token.getName()) + " Não tem permissão para excluir outros usuários");
        }

    }

    public UserApp saveUser(UserApp userApp) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name().toLowerCase());

        System.out.println(basicRole);

        userApp.setPassword( passwordEncoder.encode(userApp.getPassword()));
        userApp.setRoles(Set.of(basicRole));

        return userRepository.save(userApp);
    }

    public UserApp findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

}
