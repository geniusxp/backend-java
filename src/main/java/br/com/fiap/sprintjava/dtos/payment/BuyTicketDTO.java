package br.com.fiap.sprintjava.dtos.payment;

import br.com.fiap.sprintjava.enums.PaymentMethod;
import br.com.fiap.sprintjava.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;


public record BuyTicketDTO(
        @NotBlank
        Long ticketType,

        @NotBlank
        PaymentMethod paymentMethod,

        String couponCode
) {

}
