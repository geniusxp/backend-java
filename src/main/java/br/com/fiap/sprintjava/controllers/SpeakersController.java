package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ErrorDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.event.EventDetailsDTO;
import br.com.fiap.sprintjava.dtos.speaker.SpeakerDetailsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.sprintjava.dtos.speaker.CreateSpeakerDTO;
import br.com.fiap.sprintjava.dtos.speaker.UpdateSpeakerDTO;
import br.com.fiap.sprintjava.models.Speaker;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.repositories.SpeakerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("events/{event_id}/speakers")
@Tag(name = "Palestrantes", description = "Operações relacionadas aos palestrantes do evento.")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    @Operation(summary = "Obter todos os palestrantes", description = "Obtém todos os palestrantes do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrantes obtidos com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SpeakerDetailsDTO.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<SpeakerDetailsDTO>> getSpeakers(
            @PathVariable("event_id") Long eventId
    ) {
        var speakers = speakerRepository.findByEventId(eventId);
        return ResponseEntity.ok(speakers.stream().map(SpeakerDetailsDTO::new).toList());
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um palestrante", description = "Cria um novo palestrante para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Palestrante criado com sucesso.", content = @Content(schema = @Schema(implementation = SpeakerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> createSpeaker(
            @PathVariable("event_id") Long eventId,
            @RequestBody @Valid CreateSpeakerDTO speakerDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.findById(eventId).orElse(null);
        if(event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Evento não encontrado.", LocalDateTime.now()));
        }

        var speaker = new Speaker(
                speakerDTO.name(),
                speakerDTO.description(),
                speakerDTO.socialMediaUrl(),
                speakerDTO.language(),
                speakerDTO.avatarUrl(),
                event
        );

        speakerRepository.save(speaker);

        var uri = builder.path("/speakers/{id}").buildAndExpand(speaker.getId()).toUri();
        return ResponseEntity.created(uri).body(new SpeakerDetailsDTO(speaker));
    }

    @GetMapping("/{speaker_id}")
    @Operation(summary = "Obter um palestrante", description = "Obtém um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante obtido com sucesso.", content = @Content(schema = @Schema(implementation = SpeakerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestrante não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> getSpeaker(
            @PathVariable("speaker_id") Long speakerId
    ) {
        var speaker = speakerRepository.findById(speakerId).orElse(null);
        if(speaker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestrante não encontrado.", LocalDateTime.now()));
        }

        return ResponseEntity.ok(new SpeakerDetailsDTO(speaker));
    }

    @PutMapping("/{speaker_id}")
    @Transactional
    @Operation(summary = "Atualizar um palestrante", description = "Atualiza um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante atualizado com sucesso.", content = @Content(schema = @Schema(implementation = SpeakerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestrante não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> updateSpeaker(
            @PathVariable("speaker_id") Long speakerId,
            @RequestBody @Valid UpdateSpeakerDTO speakerDTO
    ) {
        var speaker = speakerRepository.findById(speakerId).orElse(null);
        if(speaker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestrante não encontrado.", LocalDateTime.now()));
        }

        speaker.update(speakerDTO);

        speakerRepository.save(speaker);
        return ResponseEntity.ok(new SpeakerDetailsDTO(speaker));
    }

    @DeleteMapping("/{speaker_id}")
    @Transactional
    @Operation(summary = "Deletar um palestrante", description = "Deleta um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante deletado com sucesso.", content = @Content(schema = @Schema(hidden = true), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Palestrante não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> deleteSpeaker(
            @PathVariable("speaker_id") Long speakerId
    ) {
        try {
            speakerRepository.deleteById(speakerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Palestrante não encontrado.", LocalDateTime.now()));
        }
    }
}
