package br.com.fiap.sprintjava.dtos.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEventDTO(
        @NotBlank @Size(min = 5, max = 255)
        @Schema(description = "Nome do evento", example = "FIAP Next 2024")
        String name,

        @NotBlank @Size(min = 5, max = 400)
        @Schema(description = "Descrição do evento", example = "O FIAP Next 2024 é um evento de tecnologia que reúne os melhores profissionais do mercado para compartilhar conhecimento.")
        String description,

        @NotBlank @Size(min = 10, max = 100)
        @Schema(description = "Tipo do evento", example = "Tecnologia e Inovação")
        String eventType,

        @Schema(description = "URL da imagem do evento", example = "https://avatars.com/fiapnext2024")
        String imageUrl

) {

}
