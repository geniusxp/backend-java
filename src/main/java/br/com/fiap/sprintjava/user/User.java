package br.com.fiap.sprintjava.user;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_USER")
@Data
public class User {

    @Id
    Long id_user;
    String ds_email;
    String ds_password;
    char nr_cpf;
    LocalDateTime dt_birth;
    String url_avatar;
    String tx_description;
    String tx_interests;
}
