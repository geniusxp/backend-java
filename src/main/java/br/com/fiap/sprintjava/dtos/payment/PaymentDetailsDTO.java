package br.com.fiap.sprintjava.dtos.payment;

import br.com.fiap.sprintjava.enums.PaymentMethod;
import br.com.fiap.sprintjava.enums.PaymentStatus;
import br.com.fiap.sprintjava.models.Payment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PaymentDetailsDTO(
        @Schema(description = "Id do pagamento", example = "1")
        Long id,

        @Schema(description = "Método de pagamento", example = "CREDIT_CARD")
        PaymentMethod paymentMethod,

        @Schema(description = "Status do pagamento", example = "PENDING")
        PaymentStatus status,

        @Schema(description = "Valor total do pagamento", example = "100.0")
        double totalPrice,

        @Schema(description = "Data de vencimento do pagamento", example = "2024-12-31T23:59:59")
        LocalDateTime dueDate,

        @Schema(description = "Data de pagamento", example = "2024-12-31T23:59:59")
        LocalDateTime paymentDate
) {
    public PaymentDetailsDTO(Payment payment) {
        this(
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getTotalPrice(),
                payment.getDueDate(),
                payment.getPaymentDate()
        );
    }
}
