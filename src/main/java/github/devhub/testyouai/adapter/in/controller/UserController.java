package github.devhub.testyouai.adapter.in.controller;

import github.devhub.testyouai.adapter.in.dto.UserRequestDTO;
import github.devhub.testyouai.adapter.in.mapper.UserMapper;
import github.devhub.testyouai.aplication.service.UserService;
import github.devhub.testyouai.domain.model.User;
import github.devhub.testyouai.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo ID")
    public ResponseEntity<User> getUserById(@PathVariable @NotNull Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(user);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
        try {
            User user = userMapper.toEntity(userDTO);
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    public ResponseEntity<User> updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserRequestDTO userUpdatedDTO) {
        try {
            User userUpdated = userMapper.toEntity(userUpdatedDTO);
            userService.updateUser(id, userUpdated);
            return ResponseEntity.ok().body(userUpdated);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário pelo ID")
    public ResponseEntity<Void> deleteUserByID(@PathVariable @NotNull Long id) {
        try {
            userService.removeById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
