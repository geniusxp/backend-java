package br.com.fiap.sprintjava.dtos.event;

import br.com.fiap.sprintjava.models.Event;

public record EventDetailsDTO(

        String name,

        String description,

        String eventType,

        String imageUrl
) {
    public EventDetailsDTO(Event event) {
        this(
                event.getName(),
                event.getDescription(),
                event.getEventType(),
                event.getImageUrl()
        );
    }
}
