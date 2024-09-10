package br.com.fiap.sprintjava.poll;

import br.com.fiap.sprintjava.event.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_POLL")
@Data
public class Poll {
    @Id
    int id_poll;
    String nm_poll;
    LocalDateTime dt_created_at;

    @ManyToOne
    @JoinColumn(name = "T_GXP_EVENT")
    private Event event;
}
