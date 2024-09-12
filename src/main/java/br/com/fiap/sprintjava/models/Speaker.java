package br.com.fiap.sprintjava.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_GXP_SPEAKER")
@Data
@NoArgsConstructor
public class Speaker {

    @Id
    @GeneratedValue
    @Column(name="id_speaker")
    private Long id;

    @Column(name="nm_speaker")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="url_social_media")
    private String socialMediaUrl;

    @Column(name="ds_language")
    private String language;

    @Column(name="url_avatar")
    private String avatarUrl;
}