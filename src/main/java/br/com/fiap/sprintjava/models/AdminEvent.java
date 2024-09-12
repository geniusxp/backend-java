package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_ADMINISTRATOR_EVENT")
@Data
@NoArgsConstructor
public class AdminEvent {

    @Id
    @GeneratedValue
    @Column(name="id_administrator_event")
    int id;

    @ManyToOne
    @JoinColumn(name = "id_administrator")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}