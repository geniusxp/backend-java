package br.com.fiap.sprintjava.dtos.coupon;

import jakarta.validation.constraints.NotBlank;

public record CreateCouponDTO(
        String name,
        String description,
        String code,
        double discountValue
) {
}
