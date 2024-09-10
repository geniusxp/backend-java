package br.com.fiap.sprintjava.ticket;

import br.com.fiap.sprintjava.coupon.Coupon;
import br.com.fiap.sprintjava.payment.Payment;
import br.com.fiap.sprintjava.ticketType.TicketType;
import br.com.fiap.sprintjava.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_TICKET")
@Data
public class Ticket {
    @Id
    int id_ticket;
    LocalDateTime dt_of_use;
    LocalDateTime dt_issued;
    String nr_ticket;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_ticket_type")
    private TicketType ticketType;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_coupon")
    private Coupon coupon;
}
