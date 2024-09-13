package br.com.fiap.sprintjava.dtos.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateUserDTO(
        @NotBlank @Size(min = 5, max = 150)
        @Schema(description = "Nome do usuário.", example = "Rafael Ronqui")
        String name,

        @Email
        @NotBlank @Size(min = 5, max = 320)
        @Schema(description = "E-mail do usuário.", example = "rafael.ronqui@fiap.com.br")
        String email,

        @NotBlank @Size(min = 8)
        @Schema(description = "Senha do usuário.", example = "12345678")
        String password,

        @NotBlank @Size(min = 11, max = 11)
        @Schema(description = "CPF do usuário.", example = "12345678901")
        String cpf,

        @Past
        @Schema(description = "Data de nascimento do usuário.", example = "1999-12-31")
        LocalDate birthDate,

        @Schema(description = "URL da imagem de perfil do usuário.", example = "https://api.dicebear.com/9.x/glass/svg?seed=Sasha")
        String avatarUrl,

        @Schema(description = "Descrição do usuário.", example = "Estudante de Análise e Desenvolvimento de Sistemas.")
        String description,

        @Schema(description = "Interesses do usuário.", example = "Java, Spring, Docker.")
        String interests
) {

}
