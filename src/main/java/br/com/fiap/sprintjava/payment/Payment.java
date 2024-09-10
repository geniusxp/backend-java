package br.com.fiap.sprintjava.payment;

import br.com.fiap.sprintjava.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_PAYMENT")
@Data
public class Payment {

    @Id
    int id_payment;
    String ds_payment_method;
    String st_payment;
    double vl_total_price ;
    LocalDateTime dt_due;
    LocalDateTime dt_payment;

    @OneToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;
}
