package github.devhub.testyouai.adapter.in.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String password,
        String phoneNumber
) {}
