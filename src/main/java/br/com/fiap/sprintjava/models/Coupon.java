package br.com.fiap.sprintjava.models;

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
}