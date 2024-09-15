package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("events/:id/speakers")
@Tag(name = "Palestrantes", description = "Operações relacionadas aos palestrantes do evento.")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    @Operation(summary = "Obter todos os palestrantes", description = "Obtém todos os palestrantes do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrantes obtidos com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Page<Speaker>> getSpeakers(
            @PathVariable("id") Long eventId,
            Pageable pageable
    ) {
        var speakers = speakerRepository.findByEvent(eventId, pageable);
        return ResponseEntity.ok(speakers);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um palestrante", description = "Cria um novo palestrante para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Speaker> createSpeaker(
            @PathVariable("id") Long eventId,
            @RequestBody @Valid CreateSpeakerDTO speakerDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.findById(eventId).orElseThrow();
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
        return ResponseEntity.created(uri).body(speaker);
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter um palestrante", description = "Obtém um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Speaker> getSpeaker(
            @PathVariable("id") Long speakerId
    ) {
        var speaker = speakerRepository.findById(speakerId).orElseThrow();
        return ResponseEntity.ok(speaker);
    }

    @PutMapping("/:id")
    @Transactional
    @Operation(summary = "Atualizar um palestrante", description = "Atualiza um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Speaker> updateSpeaker(
            @PathVariable("id") Long speakerId,
            @RequestBody @Valid UpdateSpeakerDTO speakerDTO
    ) {
        var speaker = speakerRepository.findById(speakerId).orElseThrow();
        speaker.update(speakerDTO);

        speakerRepository.save(speaker);
        return ResponseEntity.ok(speaker);
    }

    @DeleteMapping("/:id")
    @Transactional
    @Operation(summary = "Deletar um palestrante", description = "Deleta um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante deletado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Void> deleteSpeaker(
            @PathVariable("id") Long speakerId
    ) {
        speakerRepository.deleteById(speakerId);
        return ResponseEntity.ok().build();
    }
}
