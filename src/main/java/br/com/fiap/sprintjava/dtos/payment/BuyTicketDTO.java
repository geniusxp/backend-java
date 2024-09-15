package br.com.fiap.sprintjava.dtos.payment;

import br.com.fiap.sprintjava.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BuyTicketDTO(
        @NotNull
        @Schema(description = "Id do tipo de ingresso", example = "1")
        Long ticketTypeId,

        @NotNull
        @Schema(description = "Método de pagamento", example = "CREDIT_CARD")
        PaymentMethod paymentMethod,

        @NotBlank
        @Schema(description = "Código do cupom", example = "FIAP10")
        String couponCode
) {

}
