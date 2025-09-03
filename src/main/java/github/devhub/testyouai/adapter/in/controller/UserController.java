package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.adapter.in.dto.UserRequestDTO;
import github.devhub.testyouai.adapter.in.dto.UserResponseDTO;
import github.devhub.testyouai.adapter.in.mapper.UserMapper;
import github.devhub.testyouai.aplication.service.UserService;
import github.devhub.testyouai.domain.model.UserApp;
import github.devhub.testyouai.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Operações relacionadas a usuários")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Lista todos os usuários")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userList = userService.findAll().stream().map(userMapper::toDTO).toList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo ID")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable @NotNull Long id) {
        UserApp userApp = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponseDTO userResponseDTO = userMapper.toDTO(userApp);
        return ResponseEntity.ok(userResponseDTO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário")
    public ResponseEntity<UserApp> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
        try {
            UserApp userApp = userMapper.toEntity(userDTO);
            UserApp savedUserApp = userService.saveUser(userApp);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserApp);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    public ResponseEntity<UserApp> updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserRequestDTO userUpdatedDTO, JwtAuthenticationToken token) {
            UserApp userAppUpdated = userMapper.toEntity(userUpdatedDTO);
            userService.updateUser(id, userAppUpdated, token);
            return ResponseEntity.ok().body(userAppUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário pelo ID")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<Void> deleteUserByID(@PathVariable @NotNull Long id) {
        try {
            userService.removeById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
