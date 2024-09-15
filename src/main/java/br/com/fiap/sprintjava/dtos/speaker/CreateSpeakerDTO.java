package br.com.fiap.sprintjava.dtos.speaker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSpeakerDTO(
        @NotBlank @Size(min = 5, max = 150)
        String name,

        @NotBlank @Size(min = 5, max = 255)
        String description,

        @NotBlank @Size(min = 5, max = 255)
        String socialMediaUrl,

        @NotBlank @Size(min = 2, max = 3)
        String language,

        @NotBlank @Size(min = 5, max = 255)
        String avatarUrl
) {
}
