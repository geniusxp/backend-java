package br.com.fiap.sprintjava.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecoverPasswordDTO(
        @Email
        @NotBlank @Size(min = 5, max = 320)
        @Schema(description = "E-mail do usuário.", example = "rafael.ronqui@fiap.com.br")
        String email
) {
}
