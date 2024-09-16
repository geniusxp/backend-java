package br.com.fiap.sprintjava.dtos.event;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateEventDTO(
        @Schema(description = "Nome do evento", example = "FIAP Next 2024")
        String name,

        @Schema(description = "Descrição do evento", example = "O FIAP Next 2024 é um evento de tecnologia que reúne os melhores profissionais do mercado para compartilhar conhecimento.")
        String description,

        @Schema(description = "Tipo do evento", example = "Tecnologia e Inovação")
        String eventType,

        @Schema(description = "URL da imagem do evento", example = "https://avatars.com/fiapnext2024")
        String imageUrl
) {
}
