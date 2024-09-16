package br.com.fiap.sprintjava.dtos.eventday;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateEventDayDTO(
        @FutureOrPresent
        @NotNull @Schema(description = "Data de início do evento", example = "2022-12-31T23:59:59")
        LocalDateTime startDate,

        @FutureOrPresent
        @NotNull @Schema(description = "Data de término do evento", example = "2022-12-31T23:59:59")
        LocalDateTime endDate,

        @NotBlank @Size(min = 5, max = 255)
        @Schema(description = "URL de transmissão do evento", example = "https://transmissao.com/fiapnext2024")
        String transmissionUrl
) {
}
