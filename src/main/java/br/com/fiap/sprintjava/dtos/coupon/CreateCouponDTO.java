package br.com.fiap.sprintjava.dtos.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCouponDTO(
        @NotBlank @Size(min = 5, max = 255)
        @Schema(description = "Nome do cupom", example = "Promoção de 10 anos da FIAP")
        String name,

        @NotBlank @Size(min = 5, max = 400)
        @Schema(description = "Descrição do cupom", example = "Cupom de desconto de 10%")
        String description,

        @NotBlank @Size(min = 5, max = 15)
        @Schema(description = "Código do cupom", example = "FIAP10")
        String code,

        @DecimalMin("0.0") @DecimalMax("100.0")
        @Schema(description = "Valor do desconto", example = "10.0")
        double discountValue
) {
}
