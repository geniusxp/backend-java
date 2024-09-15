package br.com.fiap.sprintjava.dtos.speaker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSpeakerDTO(
        @Size(min = 5, max = 150)
        String name,

        @Size(min = 5, max = 255)
        String description,

        @Size(min = 5, max = 255)
        String socialMediaUrl,

        @Size(min = 2, max = 3)
        String language,

        @Size(min = 5, max = 255)
        String avatarUrl
) {
}
