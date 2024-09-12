package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_ADMINISTRATOR")
@Data
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue
    @Column(name="id_administrator")
    private Long id;

    @Column(name="ds_email")
    private String email;

    @Column(name="ds_password")
    private String password;
}