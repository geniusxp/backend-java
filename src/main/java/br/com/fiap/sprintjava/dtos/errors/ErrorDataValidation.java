package br.com.fiap.sprintjava.dtos.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.FieldError;

public record ErrorDataValidation(
        @Schema(description = "Nome do campo que apresentou erro", example = "name")
        String field,

        @Schema(description = "Mensagem de erro", example = "must not be blank")
        String message
){
    public ErrorDataValidation(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}