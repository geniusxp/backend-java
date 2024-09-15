package br.com.fiap.sprintjava.dtos.tickettype;

import br.com.fiap.sprintjava.models.TicketType;

import java.time.LocalDateTime;

public record TicketTypeDetailsDTO(
        Long id,

        double priceValue,

        String category,

        String description,

        int availableQuantity,

        int soldQuantity,

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
