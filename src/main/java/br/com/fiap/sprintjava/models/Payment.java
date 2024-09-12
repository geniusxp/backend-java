package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_PAYMENT")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    @Column(name="id_payment")
    private Long id;

    @Column(name="ds_payment_method")
    private String paymentMethod;

    @Column(name="st_payment")
    private String status;

    @Column(name="vl_total_price")
    private double totalPrice;

    @Column(name="dt_due")
    private LocalDateTime dueDate;

    @Column(name="dt_payment")
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;
}