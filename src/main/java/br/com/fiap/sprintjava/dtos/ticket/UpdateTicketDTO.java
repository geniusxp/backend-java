package br.com.fiap.sprintjava.dtos.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record UpdateTicketDTO(
        @FutureOrPresent
        @Schema(description = "Data de uso do ingresso.", example = "2024-12-31T23:59:59")
        LocalDateTime dateOfUse
) {
}
