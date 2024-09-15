package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.enums.PaymentMethod;
import br.com.fiap.sprintjava.enums.PaymentStatus;
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
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name="st_payment")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name="vl_total_price")
    private double totalPrice;

    @Column(name="dt_due")
    private LocalDateTime dueDate = LocalDateTime.now().plusDays(3);

    @Column(name="dt_payment")
    private LocalDateTime paymentDate = null;

    @OneToOne
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;

    public Payment(PaymentMethod paymentMethod, double totalPrice) {
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }
}