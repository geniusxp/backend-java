package br.com.fiap.sprintjava.dtos.coupon;

public record CreateCouponDTO(
        String name,
        String description,
        String code,
        double discountValue
) {
}
