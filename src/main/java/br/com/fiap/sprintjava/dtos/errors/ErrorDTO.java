package br.com.fiap.sprintjava.dtos.errors;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorDTO(
        @Schema(description = "Tipo do erro.", example = "Bad Request")
        String error,
        @Schema(description = "Mensagem de erro.", example = "Ocorreu um erro ao processar a requisição.")
        String message,

        @Schema(description = "Data e hora do erro.", example = "2024-06-30T19:00:00")
        LocalDateTime date
) {
    public ErrorDTO(String error, String message, LocalDateTime date) {
        this.error = error;
        this.message = message;
        this.date = date;
    }
}