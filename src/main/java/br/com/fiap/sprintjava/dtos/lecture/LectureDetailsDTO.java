package br.com.fiap.sprintjava.dtos.lecture;

import br.com.fiap.sprintjava.dtos.speaker.SpeakerDetailsDTO;
import br.com.fiap.sprintjava.models.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record LectureDetailsDTO(
        @Schema(description = "Id da palestra", example = "1")
        Long id,

        @Schema(description = "Nome da palestra", example = "Java para iniciantes")
        String name,

        @Schema(description = "Descrição da palestra", example = "Palestra destinada para quem está iniciando na linguagem Java")
        String description,

        @Schema(description = "Data e hora da palestra", example = "2024-12-31T23:59:59")
        LocalDateTime date,

        @Schema(description = "Palestrante")
        SpeakerDetailsDTO speaker
) {
    public LectureDetailsDTO(Lecture lecture) {
        this(lecture.getId(), lecture.getName(), lecture.getDescription(), lecture.getDate(), new SpeakerDetailsDTO(lecture.getSpeaker()));
    }
}
