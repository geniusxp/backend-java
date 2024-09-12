package br.com.fiap.sprintjava.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInCredentialsDTO(
        @NotBlank @Size(min = 5, max = 320)
        String email,

        @NotBlank @Size(min = 8)
        String password
) {
}
