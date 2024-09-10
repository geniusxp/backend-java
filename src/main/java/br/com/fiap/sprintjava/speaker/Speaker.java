package br.com.fiap.sprintjava.speaker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "T_GXP_SPEAKER")
@Data
public class Speaker {

    @Id
    int id_speaker;
    String nm_speaker;
    String tx_description;
    String url_social_media;
    String ds_language;
    String url_avatar;
}
