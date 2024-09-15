package br.com.fiap.sprintjava.dtos.event;

import br.com.fiap.sprintjava.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;

public record EventDetailsDTO(
        @Schema(description = "Id do evento", example = "1")
        Long id,

        @Schema(description = "Nome do evento", example = "FIAP Next 2024")
        String name,

        @Schema(description = "Descrição do evento", example = "O FIAP Next 2024 é um evento de tecnologia que reúne os melhores profissionais do mercado para compartilhar conhecimento.")
        String description,

        @Schema(description = "Tipo do evento", example = "Tecnologia e Inovação")
        String eventType,

        @Schema(description = "URL da imagem do evento", example = "https://avatars.com/fiapnext2024")
        String imageUrl
) {
    public EventDetailsDTO(Event event) {
        this(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getEventType(),
                event.getImageUrl()
        );
    }
}
