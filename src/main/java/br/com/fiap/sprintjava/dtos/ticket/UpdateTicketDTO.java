package br.com.fiap.sprintjava.dtos.ticket;

import java.time.LocalDateTime;

public record UpdateTicketDTO(
        Long id,

        LocalDateTime dateOfUse,

        LocalDateTime issuedDate,

        String ticketNumber,

        Long id_ticket_type,

        Long id_user,

        Long id_coupon,

        Long id_payment
) {
}
