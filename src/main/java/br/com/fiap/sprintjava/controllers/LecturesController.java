package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.sprintjava.dtos.lecture.CreateLectureDTO;
import br.com.fiap.sprintjava.dtos.lecture.UpdateLectureDTO;
import br.com.fiap.sprintjava.models.EventDay;
import br.com.fiap.sprintjava.models.Lecture;
import br.com.fiap.sprintjava.repositories.EventDayRepository;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.repositories.LectureRepository;
import br.com.fiap.sprintjava.repositories.SpeakerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("events/:id/lectures")
@Tag(name = "Palestras", description = "Operações relacionadas às palestras do evento.")
public class LecturesController {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDayRepository eventDayRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    @Operation(summary = "Obter todas as palestras de um dia", description = "Obtém todas as palestras do dia do evento pelo id do dia.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestras obtidas com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Page<Lecture>> getLectures(
            @PathVariable("id") Long eventDayId,
            Pageable pageable
    ) {
        var lectures = lectureRepository.findByEventDay(eventDayId, pageable);
        return ResponseEntity.ok(lectures);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar uma palestra", description = "Cria uma nova palestra para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra criada com sucesso.", content = @Content(schema = @Schema(implementation = Lecture.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Lecture> createLecture(
            @RequestBody @Valid CreateLectureDTO lectureDTO,
            UriComponentsBuilder builder
    ) {
        var eventDay = eventDayRepository.findById(lectureDTO.eventDayId()).orElseThrow();
        var speaker = speakerRepository.findById(lectureDTO.speakerId()).orElseThrow();
        var lecture = new Lecture(
                lectureDTO.name(),
                lectureDTO.description(),
                lectureDTO.date(),
                eventDay,
                speaker
        );

        lectureRepository.save(lecture);

        var uri = builder.path("/lectures/{id}").buildAndExpand(lecture.getId()).toUri();
        return ResponseEntity.created(uri).body(lecture);
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter uma palestra", description = "Obtém uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra obtida com sucesso.", content = @Content(schema = @Schema(implementation = Lecture.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Lecture> getLecture(
            @PathVariable("id") Long lectureId
    ) {
        var lecture = lectureRepository.findById(lectureId).orElseThrow();

        return ResponseEntity.ok(lecture);
    }

    @PutMapping("/:id")
    @Transactional
    @Operation(summary = "Atualizar uma palestra", description = "Atualiza uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra atualizada com sucesso.", content = @Content(schema = @Schema(implementation = Lecture.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Lecture> updateLecture(
            @PathVariable("id") Long lectureId,
            @RequestBody @Valid UpdateLectureDTO lectureDTO
    ) {
        var lecture = lectureRepository.findById(lectureId).orElseThrow();

        lecture.update(lectureDTO);
        lectureRepository.save(lecture);

        return ResponseEntity.ok(lecture);
    }

    @DeleteMapping("/:id")
    @Transactional
    @Operation(summary = "Deletar uma palestra", description = "Deleta uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra deletada com sucesso.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Void> deleteLecture(
            @PathVariable("id") Long lectureId
    ) {
        lectureRepository.deleteById(lectureId);
        return ResponseEntity.ok().build();
    }
}
