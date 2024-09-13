package br.com.fiap.sprintjava.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecoverPasswordDTO(
        @Email
        @NotBlank @Size(min = 5, max = 320)
        String email
) {
}
