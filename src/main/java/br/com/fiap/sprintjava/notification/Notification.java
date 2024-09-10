package br.com.fiap.sprintjava.notification;

import br.com.fiap.sprintjava.event.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_NOTIFICATION")
@Data
public class Notification {

    @Id
    int id_notification;
    LocalDateTime ds_notification;
    String ds_notification_type;
    String tx_content;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}
