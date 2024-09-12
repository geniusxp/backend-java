package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_EVENT")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Column(name="id_event")
    private Long id;

    @Column(name="nm_event")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="ds_event_type")
    private String eventType;

    @Column(name="url_image")
    private String imageUrl;
}
