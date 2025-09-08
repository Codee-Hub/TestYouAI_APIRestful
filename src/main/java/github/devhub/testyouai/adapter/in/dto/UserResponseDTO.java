package github.devhub.testyouai.adapter.in.dto;

import github.devhub.testyouai.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String password,
        String phoneNumber,
        Set<Role> roles
) {}
