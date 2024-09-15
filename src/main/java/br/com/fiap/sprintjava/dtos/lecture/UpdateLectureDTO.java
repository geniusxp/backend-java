package br.com.fiap.sprintjava.dtos.lecture;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateLectureDTO(
        @Size(min = 5, max = 150)
        String name,

        @Size(min = 5, max = 255)
        String description,

        @FutureOrPresent
        LocalDateTime date,

        Long eventDayId,

        Long speakerId
) {
}
