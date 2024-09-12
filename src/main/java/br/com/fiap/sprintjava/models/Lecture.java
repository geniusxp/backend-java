package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_LECTURE")
@Data
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name="id_lecture")
    private Long id;

    @Column(name="nm_lecture")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="dt_lecture")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_event_day")
    private EventDay eventDay;

    @ManyToOne
    @JoinColumn(name = "id_speaker")
    private Speaker speaker;
}
