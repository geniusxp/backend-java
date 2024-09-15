package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_POLL")
@Data
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue
    @Column(name="id_poll")
    private Long id;

    @Column(name="nm_poll")
    String name;

    @Column(name="dt_start_at")
    LocalDateTime startDate = LocalDateTime.now();

    @Column(name="dt_end_at")
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(15);

    @ManyToOne
    @JoinColumn(name = "T_GXP_EVENT")
    private Event event;
}
