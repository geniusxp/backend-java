package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_POLL_OPTION")
@Data
@NoArgsConstructor
public class PollOption {
    @Id
    @GeneratedValue
    @Column(name="id_poll_option")
    private Long id;

    @Column(name="tx_description")
    private String description;

    @Column(name="nr_votes")
    private int votesAmount;

    @ManyToOne
    @JoinColumn(name = "id_poll")
    private Poll poll;
}