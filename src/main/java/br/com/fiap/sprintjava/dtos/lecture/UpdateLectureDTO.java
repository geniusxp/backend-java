package br.com.fiap.sprintjava.dtos.lecture;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateLectureDTO(
        @Size(min = 5, max = 150)
        @Schema(description = "Nome da palestra", example = "Java para iniciantes")
        String name,

        @Size(min = 5, max = 255)
        @Schema(description = "Descrição da palestra", example = "Palestra destinada para quem está iniciando na linguagem Java")
        String description,

        @FutureOrPresent
        @Schema(description = "Data e hora da palestra", example = "2022-12-31T23:59:59")
        LocalDateTime date,

        @NotNull
        @Schema(description = "Id do dia do evento", example = "1")
        Long eventDayId,

        @NotNull
        @Schema(description = "Id do palestrante", example = "1")
        Long speakerId
) {
}
