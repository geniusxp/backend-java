package br.com.fiap.sprintjava.pollOption;

import br.com.fiap.sprintjava.poll.Poll;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T_GXP_POLL_OPTION")
@Data
public class PollOption {
    @Id
    int id_poll_option;
    String tx_description;
    int nr_votes;

    @ManyToOne
    @JoinColumn(name = "id_poll")
    private Poll poll;
}
