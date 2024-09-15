package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.speaker.UpdateSpeakerDTO;
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

    @ManyToOne
    @JoinColumn(name="id_event")
    private Event event;

    public Speaker(String name, String description, String socialMediaUrl, String language, String avatarUrl, Event event) {
        this.name = name;
        this.description = description;
        this.socialMediaUrl = socialMediaUrl;
        this.language = language;
        this.avatarUrl = avatarUrl;
        this.event = event;
    }

    public void update(UpdateSpeakerDTO speakerDTO) {
        if(speakerDTO.name() != null) this.name = speakerDTO.name();
        if(speakerDTO.description() != null) this.description = speakerDTO.description();
        if(speakerDTO.socialMediaUrl() != null) this.socialMediaUrl = speakerDTO.socialMediaUrl();
        if(speakerDTO.language() != null) this.language = speakerDTO.language();
        if(speakerDTO.avatarUrl() != null) this.avatarUrl = speakerDTO.avatarUrl();
    }
}