package br.com.fiap.sprintjava.models;


import br.com.fiap.sprintjava.dtos.eventday.CreateEventDayDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "eventDay")
    private List<Lecture> lectures = List.of();

    public EventDay(CreateEventDayDTO eventDayDTO, Event evt) {
        this.startDate = eventDayDTO.startDate();
        this.endDate = eventDayDTO.endDate();
        this.transmissionUrl = eventDayDTO.transmissionUrl();
        this.event = evt;
    }
}