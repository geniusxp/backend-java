package br.com.fiap.sprintjava.dtos.coupon;

import br.com.fiap.sprintjava.models.Coupon;
import io.swagger.v3.oas.annotations.media.Schema;

public record CouponDetailsDTO(
        @Schema(description = "Id do cupom", example = "1")
        Long id,

        @Schema(description = "Nome do cupom", example = "Promoção de 10 anos da FIAP")
        String name,

        @Schema(description = "Descrição do cupom", example = "Cupom de desconto de 10%")
        String description,

        @Schema(description = "Código do cupom", example = "FIAP10")
        String code,

        @Schema(description = "Valor do desconto", example = "10.0")
        double discountValue
) {
    public CouponDetailsDTO(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getName(),
                coupon.getDescription(),
                coupon.getCode(),
                coupon.getDiscountValue()
        );
    }
}
