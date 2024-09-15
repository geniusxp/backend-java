package br.com.fiap.sprintjava.dtos.lecture;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateLectureDTO(
        @NotBlank @Size(min = 5, max = 150)
        String name,

        @NotBlank @Size(min = 5, max = 255)
        String description,

        @FutureOrPresent
        LocalDateTime date,

        @NotBlank
        Long eventDayId,

        @NotBlank
        Long speakerId
) {
}
