package br.com.fiap.sprintjava.vote;

import br.com.fiap.sprintjava.pollOption.PollOption;
import br.com.fiap.sprintjava.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_VOTE")
@Data
public class Vote {

    @Id
    int id_vote;
    LocalDateTime dt_created_at;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_poll_option ")
    private PollOption pollOption;
}
