package br.com.fiap.sprintjava.dtos.tickettype;

import br.com.fiap.sprintjava.models.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record TicketTypeDetailsDTO(
        @Schema(description = "Id do tipo de ingresso", example = "1")
        Long id,

        @Schema(description = "Preço do ingresso", example = "100.0")
        double priceValue,

        @Schema(description = "Categoria do ingresso", example = "Meia-entrada")
        String category,

        @Schema(description = "Descrição do ingresso", example = "Meia-entrada destinada para estudntes, idosos, pessoas com deficiência e jovens de baixa renda.")
        String description,

        @Schema(description = "Quantidade disponível do ingresso", example = "100")
        int availableQuantity,

        @Schema(description = "Quantidade vendida do ingresso", example = "50")
        int soldQuantity,

        @Schema(description = "Data e hora de encerramento da venda do ingresso", example = "2024-12-31T23:59:59")
        LocalDateTime finishedAt
) {
    public TicketTypeDetailsDTO(TicketType ticketType) {
        this(
                ticketType.getId(),
                ticketType.getPriceValue(),
                ticketType.getCategory(),
                ticketType.getDescription(),
                ticketType.getAvailableQuantity(),
                ticketType.getSoldQuantity(),
                ticketType.getFinishedAt()
        );
    }
}
