package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.payment.BuyTicketDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "T_GXP_TICKET")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    @Column(name="id_ticket")
    private Long id;

    @Column(name="dt_of_use")
    private LocalDateTime dateOfUse = null;

    @Column(name="dt_issued")
    private LocalDateTime issuedDate;

    @Column(name="nr_ticket")
    private String ticketNumber;

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
    private Coupon coupon = null;

    public Ticket(User user, TicketType ticketType, Payment payment, Coupon coupon) {
        this.issuedDate = LocalDateTime.now();
        this.ticketNumber = Instant.now().getEpochSecond() + "@" + user.getId();
        this.user = user;
        this.ticketType = ticketType;
        this.payment = payment;
        this.coupon = coupon;

    }
}