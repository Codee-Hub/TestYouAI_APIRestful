package github.devhub.testyouai.aplication.service;

import github.devhub.testyouai.adapter.out.repository.UserRepository;
import github.devhub.testyouai.domain.model.UserApp;
import github.devhub.testyouai.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace this with your entity and logic
        UserApp userApp = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(3L));

        return new org.springframework.security.core.userdetails.User(
                userApp.getName(),
                userApp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }

}
