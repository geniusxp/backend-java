package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ErrorDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.lecture.LectureDetailsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.sprintjava.dtos.lecture.CreateLectureDTO;
import br.com.fiap.sprintjava.dtos.lecture.UpdateLectureDTO;
import br.com.fiap.sprintjava.models.Lecture;
import br.com.fiap.sprintjava.repositories.EventDayRepository;
import br.com.fiap.sprintjava.repositories.LectureRepository;
import br.com.fiap.sprintjava.repositories.SpeakerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@Tag(name = "Palestras", description = "Operações relacionadas às palestras do evento.")
public class LecturesController {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EventDayRepository eventDayRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping("/event-days/{event_day_id}/lectures")
    @Operation(summary = "Obter todas as palestras de um dia", description = "Obtém todas as palestras do dia do evento pelo id do dia.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestras obtidas com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<LectureDetailsDTO>> getLectures(
            @PathVariable("event_day_id") Long eventDayId
    ) {
        var lectures = lectureRepository.findByEventDayId(eventDayId);
        return ResponseEntity.ok(lectures.stream().map(LectureDetailsDTO::new).toList());
    }

    @PostMapping("/event-days/{event_day_id}/lectures")
    @Transactional
    @Operation(summary = "Criar uma palestra", description = "Cria uma nova palestra para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Palestra criada com sucesso.", content = @Content(schema = @Schema(implementation = LectureDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Dia do evento não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Palestrante não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> createLecture(
            @PathVariable("event_day_id") Long eventDayId,
            @RequestBody @Valid CreateLectureDTO lectureDTO,
            UriComponentsBuilder builder
    ) {
        var eventDay = eventDayRepository.findById(eventDayId).orElse(null);
        if(eventDay == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("eventDayId", "Dia do evento não encontrado.", LocalDateTime.now()));
        }

        var speaker = speakerRepository.findById(lectureDTO.speakerId()).orElse(null);
        if(speaker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("speakerId", "Palestrante não encontrado.", LocalDateTime.now()));
        }

        var lecture = new Lecture(
                lectureDTO.name(),
                lectureDTO.description(),
                lectureDTO.date(),
                eventDay,
                speaker
        );

        lectureRepository.save(lecture);

        var uri = builder.path("/lectures/{lecture_id}").buildAndExpand(lecture.getId()).toUri();
        return ResponseEntity.created(uri).body(new LectureDetailsDTO(lecture));
    }

    @GetMapping("/lectures/{lecture_id}")
    @Operation(summary = "Obter uma palestra", description = "Obtém uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra obtida com sucesso.", content = @Content(schema = @Schema(implementation = LectureDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestra não encontrada.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> getLecture(
            @PathVariable("lecture_id") Long lectureId
    ) {
        var lecture = lectureRepository.findById(lectureId).orElse(null);
        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestra não encontrada.", LocalDateTime.now()));
        }

        return ResponseEntity.ok(new LectureDetailsDTO(lecture));
    }

    @PutMapping("/lectures/{lecture_id}")
    @Transactional
    @Operation(summary = "Atualizar uma palestra", description = "Atualiza uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra atualizada com sucesso.", content = @Content(schema = @Schema(implementation = LectureDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestra não encontrada.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> updateLecture(
            @PathVariable("lecture_id") Long lectureId,
            @RequestBody @Valid UpdateLectureDTO lectureDTO
    ) {
        var lecture = lectureRepository.findById(lectureId).orElse(null);
        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestra não encontrada.", LocalDateTime.now()));
        }

        lecture.update(lectureDTO);
        lectureRepository.save(lecture);

        return ResponseEntity.ok(new LectureDetailsDTO(lecture));
    }

    @DeleteMapping("/lectures/{lecture_id}")
    @Transactional
    @Operation(summary = "Deletar uma palestra", description = "Deleta uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra deletada com sucesso.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestra não encontrada.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> deleteLecture(
            @PathVariable("lecture_id") Long lectureId
    ) {
        try {
            lectureRepository.deleteById(lectureId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestra não encontrada.", LocalDateTime.now()));
        }
    }
}
