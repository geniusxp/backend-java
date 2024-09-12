package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_NOTIFICATION")
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    @Column(name="id_notification")
    private Long id;

    @Column(name="ds_notification")
    private LocalDateTime description;

    @Column(name="ds_notification_type")
    private String type;

    @Column(name="tx_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}