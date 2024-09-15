package br.com.fiap.sprintjava.dtos.tickettype;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateTicketTypeDTO(
        @Schema(description = "Valor do tipo de ingresso.", example = "50.00")        
        double priceValue,

        @NotBlank @Size(min = 5, max = 150)
        @Schema(description = "Categoria do tipo de ingresso.", example = "Meia-entrada")
        String category,

        @NotBlank @Size(min = 5, max = 150)
        @Schema(description = "Descrição do tipo de ingresso.", example = "Meia-entrada destinada para estudntes, idosos, pessoas com deficiência e jovens de baixa renda.")
        String description,

        @Schema(description = "Quantidade disponível do tipo de ingresso.", example = "100")
        int availableQuantity,

        @Future
        @Schema(description = "Data e hora de encerramento da venda do tipo de ingresso.", example = "2024-12-31T23:59:59")
        LocalDateTime finishedAt
) {
}
