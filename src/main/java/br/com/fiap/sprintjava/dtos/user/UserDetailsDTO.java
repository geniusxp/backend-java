package br.com.fiap.sprintjava.dtos.user;

import br.com.fiap.sprintjava.models.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UserDetailsDTO(
        @Schema(description = "ID do usuário.", example = "1")
        Long id,

        @Schema(description = "Nome do usuário.", example = "Rafael Ronqui")
        String name,

        @Schema(description = "E-mail do usuário.", example = "rafael.ronqui@fiap.com.br")
        String email,

        @Schema(description = "CPF do usuário.", example = "12345678901")
        String cpf,

        @Schema(description = "Data de nascimento do usuário.", example = "1999-12-31")
        LocalDate birthDate,

        @Schema(description = "URL da imagem de perfil do usuário.", example = "https://api.dicebear.com/9.x/glass/svg?seed=Sasha")
        String avatarUrl,

        @Schema(description = "Descrição do usuário.", example = "Estudante de Análise e Desenvolvimento de Sistemas.")
        String description,

        @Schema(description = "Interesses do usuário.", example = "Java, Spring, Docker.")
        String interests
) {
    public UserDetailsDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getBirthDate(),
                user.getAvatarUrl(),
                user.getDescription(),
                user.getInterests()
        );
    }
}
