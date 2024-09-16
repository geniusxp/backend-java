package br.com.fiap.sprintjava.dtos.speaker;

import br.com.fiap.sprintjava.dtos.event.EventDetailsDTO;
import br.com.fiap.sprintjava.models.Event;
import br.com.fiap.sprintjava.models.Speaker;
import io.swagger.v3.oas.annotations.media.Schema;

public record SpeakerDetailsDTO(
        @Schema(description = "ID do palestrante", example = "1")
        Long id,

        @Schema(description = "Nome do palestrante", example = "João da Silva")
        String name,

        @Schema(description = "Descrição do palestrante", example = "João da Silva é um desenvolvedor Java com mais de 10 anos de experiência")
        String description,

        @Schema(description = "URL de rede social do palestrante", example = "https://linkedin.com/joaodasilva")
        String socialMediaUrl,

        @Schema(description = "Idioma do palestrante", example = "PT")
        String language,

        @Schema(description = "URL da foto do palestrante", example = "https://avatars.com/joaodasilva")
        String avatarUrl
) {
    public SpeakerDetailsDTO(Speaker speaker) {
        this(
                speaker.getId(),
                speaker.getName(),
                speaker.getDescription(),
                speaker.getSocialMediaUrl(),
                speaker.getLanguage(),
                speaker.getAvatarUrl()
        );
    }
}
