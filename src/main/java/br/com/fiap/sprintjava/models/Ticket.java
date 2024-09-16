package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.payment.BuyTicketDTO;
import br.com.fiap.sprintjava.dtos.ticket.UpdateTicketDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ticket", orphanRemoval = true)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_coupon")
    private Coupon coupon = null;

    public Ticket(BuyTicketDTO ticketDTO, User user, TicketType ticketType, Coupon coupon) {
        this.issuedDate = LocalDateTime.now();
        this.ticketNumber = Instant.now().getEpochSecond() + "@" + user.getId();
        this.user = user;
        this.ticketType = ticketType;
        this.coupon = coupon;
        this.payment = new Payment(ticketDTO.paymentMethod(), ticketType.getPriceValue() - (coupon == null ? 0 : coupon.getDiscountValue()));
        this.payment.setTicket(this);
    }

    public void update(UpdateTicketDTO dto){
        if(dto.dateOfUse() != null)
            this.dateOfUse = LocalDateTime.now();
    }
}