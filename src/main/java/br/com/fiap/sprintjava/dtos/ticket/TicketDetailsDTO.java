package br.com.fiap.sprintjava.dtos.ticket;

import br.com.fiap.sprintjava.models.Ticket;

import java.time.LocalDateTime;

public record TicketDetailsDTO(
        Long id,

        LocalDateTime dateOfUse,

        LocalDateTime issuedDate,

        String ticketNumber,

        Long id_ticket_type,

        Long id_user,

        Long id_coupon,

        Long id_payment

) {
    public TicketDetailsDTO(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getDateOfUse(),
                ticket.getIssuedDate(),
                ticket.getTicketNumber(),
                ticket.getCoupon() == null ? null : ticket.getCoupon().getId(),
                ticket.getPayment().getId(),
                ticket.getTicketType().getId(),
                ticket.getUser().getId()
        );
    }
}
