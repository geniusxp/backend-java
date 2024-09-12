package br.com.fiap.sprintjava.dtos.user;

import br.com.fiap.sprintjava.models.User;

import java.time.LocalDate;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        String cpf,
        LocalDate birthDate,
        String avatarUrl,
        String description,
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
