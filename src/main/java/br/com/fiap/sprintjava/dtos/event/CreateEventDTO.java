package br.com.fiap.sprintjava.dtos.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEventDTO(
        @NotBlank @Size(min = 5, max = 255)
        String name,

        @NotBlank @Size(min = 5, max = 400)
        String description,

        @NotBlank @Size(min = 10, max = 100)
        String eventType,

        String imageUrl

) {

}
