package br.com.fiap.sprintjava.adm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.Data;

@Entity
@Table(name = "T_GXP_ADMINISTRATOR")
@Data
public class Adm {

    @Id
    int id_administrator;
    String ds_email;
    String ds_password;
}
