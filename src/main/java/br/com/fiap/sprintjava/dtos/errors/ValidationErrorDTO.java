package br.com.fiap.sprintjava.dtos.errors;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorDTO(
        @Schema(description = "Tipo do erro.", example = "Validation Error")
        String error,

        @Schema(description = "Lista de campos com erro.")
        List<ErrorDataValidation> fields,

        @Schema(description = "Data e hora do erro.", example = "2024-06-30T19:00:00")
        LocalDateTime date
) {
    public ValidationErrorDTO(String error, List<ErrorDataValidation> fields, LocalDateTime date) {
        this.error = error;
        this.fields = fields;
        this.date = date;
    }
}
