package br.com.fiap.sprintjava.dtos.event;

public record UpdateEventDTO(
        Long id,

        String name,

        String description,

        String eventType,

        String imageUrl
) {
}
