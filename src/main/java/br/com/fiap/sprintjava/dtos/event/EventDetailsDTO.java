package br.com.fiap.sprintjava.dtos.event;

import br.com.fiap.sprintjava.models.Event;

public record EventDetailsDTO(
        Long id,

        String name,

        String description,

        String eventType,

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
