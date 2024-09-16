package br.com.fiap.sprintjava.dtos.eventday;

import br.com.fiap.sprintjava.dtos.lecture.LectureDetailsDTO;
import br.com.fiap.sprintjava.models.EventDay;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record EventDayPreviewDTO(
        @Schema(description = "Id do dia do evento", example = "1")
        Long id,

        @Schema(description = "Data de início do dia", example = "2024-12-31T23:59:59")
        LocalDateTime startDate
) {
    public EventDayPreviewDTO(EventDay eventDay) {
        this(eventDay.getId(), eventDay.getStartDate());
    }
}
