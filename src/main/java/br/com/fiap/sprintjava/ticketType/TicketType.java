package br.com.fiap.sprintjava.ticketType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table (name = "T_GXP_TICKET_TYPE")
@Data
public class TicketType {

    @Id
    int id_ticket_type;
    double vl_preice;
    String ds_category;
    String tx_descrition;
    int nr_quantity;
    int nr_sold;
    LocalDateTime dt_fineished_at;

}
