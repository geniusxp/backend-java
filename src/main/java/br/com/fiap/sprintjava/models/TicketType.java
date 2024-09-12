package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "T_GXP_TICKET_TYPE")
@Data
@NoArgsConstructor
public class TicketType {
    @Id
    @GeneratedValue
    @Column(name="id_ticket_type")
    private Long id;

    @Column(name="vl_price")
    private double priceValue;

    @Column(name="ds_category")
    private String category;

    @Column(name="tx_description")
    private String description;

    @Column(name="nr_quantity")
    private int availableQuantity;

    @Column(name="nr_sold")
    private int soldQuantity;

    @Column(name="dt_finished_at")
    private LocalDateTime finishedAt;
}