package br.com.fiap.sprintjava.dtos.payment;

import br.com.fiap.sprintjava.models.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record TicketDetailsDTO(
        @Schema(description = "Id do ingresso", example = "1")
        Long id,

        @Schema(description = "Data de uso do ingresso", example = "2022-12-31T23:59:59")
        LocalDateTime dateOfUse,

        @Schema(description = "Data de emissão do ingresso", example = "2022-12-31T23:59:59")
        LocalDateTime issuedDate,

        @Schema(description = "Número do ingresso", example = "123456")
        String ticketNumber,

        @Schema(description = "Id do usuário", example = "1")
        Long userId,

        @Schema(description = "Id do tipo de ingresso", example = "1")
        Long ticketTypeId,

        @Schema(description = "Id do pagamento", example = "1")
        Long paymentId,

        @Schema(description = "Id do cupom", example = "1")
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
