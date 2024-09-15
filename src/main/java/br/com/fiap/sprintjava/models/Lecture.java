package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.lecture.UpdateLectureDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_GXP_LECTURE")
@Data
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name="id_lecture")
    private Long id;

    @Column(name="nm_lecture")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="dt_lecture")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_event_day")
    private EventDay eventDay;

    @ManyToOne
    @JoinColumn(name = "id_speaker")
    private Speaker speaker;

    public Lecture(String name, String description, LocalDateTime date, EventDay eventDay, Speaker speaker) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.eventDay = eventDay;
        this.speaker = speaker;
    }

    public void update(UpdateLectureDTO lectureDTO) {
        if(lectureDTO.name() != null) this.name = lectureDTO.name();
        if(lectureDTO.description() != null) this.description = lectureDTO.description();
        if(lectureDTO.date() != null) this.date = lectureDTO.date();
        if(lectureDTO.eventDayId() != null) this.eventDay.setId(lectureDTO.eventDayId());
        if(lectureDTO.speakerId() != null) this.speaker.setId(lectureDTO.speakerId());
    }
}
