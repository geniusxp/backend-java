package br.com.fiap.sprintjava.dtos.speaker;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateSpeakerDTO(
        @Size(min = 5, max = 150)
        @Schema(description = "Nome do palestrante", example = "João da Silva")
        String name,

        @Size(min = 5, max = 255)
        @Schema(description = "Descrição do palestrante", example = "João da Silva é um desenvolvedor Java com mais de 10 anos de experiência")
        String description,

        @Size(min = 5, max = 255)
        @Schema(description = "URL de rede social do palestrante", example = "https://linkedin.com/joaodasilva")
        String socialMediaUrl,

        @Size(min = 2, max = 3)
        @Schema(description = "Idioma do palestrante", example = "PT")
        String language,

        @Size(min = 5, max = 255)
        @Schema(description = "URL da foto do palestrante", example = "https://avatars.com/joaodasilva")
        String avatarUrl
) {
}
