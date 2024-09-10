package br.com.fiap.sprintjava.admEvent;

import br.com.fiap.sprintjava.adm.Adm;
import br.com.fiap.sprintjava.event.Event;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T_GXP_ADMINISTRATOR_EVENT")
@Data
public class AdmEvent {

    @Id
    int id_administrator_event;

    @ManyToOne
    @JoinColumn(name = "id_administrator")
    private Adm adm;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}
