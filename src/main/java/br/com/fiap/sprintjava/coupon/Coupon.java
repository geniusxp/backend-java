package br.com.fiap.sprintjava.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "T_GXP_COUPON")
@Data
public class Coupon {

    @Id
    int id_coupon;
    String nm_coupon;
    String tx_description;
    String cod_coupon;
    double vl_discount;
}
