package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.coupon.CreateCouponDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_COUPON")
@Data
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    @Column(name="id_coupon")
    private Long id;

    @Column(name="nm_coupon")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="cod_coupon")
    private String code;

    @Column(name="vl_discount")
    private double discountValue;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    public Coupon(CreateCouponDTO createCouponDTO, Event event) {
        this.name = createCouponDTO.name();
        this.description = createCouponDTO.description();
        this.code = createCouponDTO.code();
        this.discountValue = createCouponDTO.discountValue();
        this.event = event;
    }
}