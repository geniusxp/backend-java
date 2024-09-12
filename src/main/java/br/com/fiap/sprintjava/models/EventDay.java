package br.com.fiap.sprintjava.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_EVENT_DAY")
@Data
@NoArgsConstructor
public class EventDay {
    @Id
    @GeneratedValue
    @Column(name="id_event_day")
    private Long id;

    @Column(name="dt_start")
    private LocalDateTime startDate;

    @Column(name="dt_end")
    private LocalDateTime endDate;

    @Column(name="url_transmission")
    private String transmissionUrl;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}