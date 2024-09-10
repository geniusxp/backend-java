package br.com.fiap.sprintjava.lecture;

import br.com.fiap.sprintjava.eventDay.EventDay;
import br.com.fiap.sprintjava.speaker.Speaker;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_LECTURE")
@Data
public class Lecture {

    @Id
    int id_lecture;
    String nm_lecture;
    String tx_description;
    LocalDateTime dt_lecture;

    @ManyToOne
    @JoinColumn(name = "id_event_day")
    private EventDay event_day;

    @ManyToOne
    @JoinColumn(name = "id_speaker")
    private Speaker speaker;
}
