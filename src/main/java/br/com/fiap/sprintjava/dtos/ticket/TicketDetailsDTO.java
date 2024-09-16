package br.com.fiap.sprintjava.dtos.ticket;

import br.com.fiap.sprintjava.dtos.coupon.CouponDetailsDTO;
import br.com.fiap.sprintjava.dtos.event.EventDetailsDTO;
import br.com.fiap.sprintjava.dtos.payment.PaymentDetailsDTO;
import br.com.fiap.sprintjava.dtos.tickettype.ShortTicketTypeDetailsDTO;
import br.com.fiap.sprintjava.models.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record TicketDetailsDTO(
        @Schema(description = "Id do ingresso", example = "1")
        Long id,

        @Schema(description = "Data de uso do ingresso", example = "2024-12-31T23:59:59")
        LocalDateTime dateOfUse,

        @Schema(description = "Data de emissão do ingresso", example = "2024-12-31T23:59:59")
        LocalDateTime issuedDate,

        @Schema(description = "Número do ingresso", example = "123456789")
        String ticketNumber,

        @Schema(description = "Detalhes do pagamento")
        PaymentDetailsDTO payment,

        @Schema(description = "Detalhes do cupom")
        CouponDetailsDTO coupon,

        @Schema(description = "Detalhes do tipo de ingresso")
        ShortTicketTypeDetailsDTO ticketType,

        @Schema(description = "Detalhes do evento")
        EventDetailsDTO event

) {
    public TicketDetailsDTO(Ticket ticket) {
        this(
                ticket.getId(),
                ticket.getDateOfUse(),
                ticket.getIssuedDate(),
                ticket.getTicketNumber(),
                new PaymentDetailsDTO(ticket.getPayment()),
                ticket.getCoupon() == null ? null : new CouponDetailsDTO(ticket.getCoupon()),
                new ShortTicketTypeDetailsDTO(ticket.getTicketType()),
                new EventDetailsDTO(ticket.getTicketType().getEvent())
        );
    }
}
