package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_VOTE")
@Data
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    @Column(name="id_vote")
    private Long id;

    @Column(name="dt_created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_poll_option ")
    private PollOption pollOption;
}