package br.com.fiap.sprintjava.dtos.payment;

import br.com.fiap.sprintjava.models.*;

import java.time.LocalDateTime;

public record TicketDetailsDTO(
        Long id,

        LocalDateTime dateOfUse,

        LocalDateTime issuedDate,

        String ticketNumber,

        Long userId,

        Long ticketTypeId,

        Long paymentId,

        Long couponId
) {
    public TicketDetailsDTO(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getDateOfUse(),
                ticket.getIssuedDate(),
                ticket.getTicketNumber(),
                ticket.getUser().getId(),
                ticket.getTicketType().getId(),
                ticket.getPayment().getId(),
                ticket.getCoupon() == null ? null : ticket.getCoupon().getId()
        );
    }
}
