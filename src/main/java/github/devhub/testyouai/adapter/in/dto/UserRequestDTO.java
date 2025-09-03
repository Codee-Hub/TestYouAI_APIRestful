package github.devhub.testyouai.adapter.in.dto;

import jakarta.validation.constraints.*;

public record UserRequestDTO(
        @NotBlank @Size(min = 3, max = 20)
        String name,
        @Email @NotBlank
        String email,
        @NotBlank @Size(min = 3, max = 20)
        String password,
        @NotBlank
        String phoneNumber
) {}
