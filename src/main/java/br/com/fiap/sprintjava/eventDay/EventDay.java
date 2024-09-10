package br.com.fiap.sprintjava.eventDay;

import br.com.fiap.sprintjava.event.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_EVENT_DAY")
@Data
public class EventDay {

    @Id
    int id_event_day;
    LocalDateTime dt_start;
    LocalDateTime dt_end;
    String url_transmission;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}
