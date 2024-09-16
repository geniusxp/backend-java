package br.com.fiap.sprintjava.dtos.tickettype;

import br.com.fiap.sprintjava.models.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ShortTicketTypeDetailsDTO(
        @Schema(description = "Id do tipo de ingresso", example = "1")
        Long id,

        @Schema(description = "Preço do ingresso", example = "100.0")
        double priceValue,

        @Schema(description = "Categoria do ingresso", example = "Meia-entrada")
        String category,

        @Schema(description = "Descrição do ingresso", example = "Meia-entrada destinada para estudntes, idosos, pessoas com deficiência e jovens de baixa renda.")
        String description
) {
    public ShortTicketTypeDetailsDTO(TicketType ticketType) {
        this(
                ticketType.getId(),
                ticketType.getPriceValue(),
                ticketType.getCategory(),
                ticketType.getDescription()
        );
    }
}
