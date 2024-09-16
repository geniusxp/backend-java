package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ErrorDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.eventday.CreateEventDayDTO;
import br.com.fiap.sprintjava.dtos.eventday.EventDayDetailsDTO;
import br.com.fiap.sprintjava.dtos.eventday.EventDayPreviewDTO;
import br.com.fiap.sprintjava.models.EventDay;
import br.com.fiap.sprintjava.repositories.EventDayRepository;
import br.com.fiap.sprintjava.repositories.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Dias de Evento", description = "Operações relacionadas aos dias de evento.")
public class EventDayController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDayRepository eventDayRepository;

    @PostMapping("/events/{event_id}/days")
    @Transactional
    @Operation(summary = "Criar um dia de evento", description = "Cria um novo dia de evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dia de evento criado com sucesso.", content = @Content(schema = @Schema(implementation = EventDayDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "Dia de evento já cadastrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> createEventDay(
            @PathVariable("event_id") Long eventId,
            @RequestBody @Valid CreateEventDayDTO eventDayDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.getReferenceById(eventId);

        if(event.getEventDays().stream().anyMatch(eventDay -> eventDay.getStartDate().isEqual(eventDayDTO.startDate()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Conflict", "Dia de evento já cadastrado.", LocalDateTime.now()));
        }

        var newEventDay = new EventDay(eventDayDTO, event);

        eventDayRepository.save(newEventDay);

        var url = builder.path("events/days/{id}").buildAndExpand(newEventDay.getId()).toUri();
        return ResponseEntity.created(url).body(new EventDayDetailsDTO(newEventDay));
    }

    @GetMapping("/event-days/{id}")
    @Operation(summary = "Obter um dia de evento", description = "Obtém um dia de evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dia de evento obtido com sucesso.", content = @Content(schema = @Schema(implementation = EventDayDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Dia de evento não encontrado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<EventDayDetailsDTO> getEventDay(@PathVariable("id") Long eventDayId) {
        var eventDay = eventDayRepository.findById(eventDayId).orElse(null);
        if (eventDay == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new EventDayDetailsDTO(eventDay));
    }

    @GetMapping("/events/{event_id}/days")
    @Operation(summary = "Obter todos os dias de evento", description = "Obtém todos os dias de evento pelo id do evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dias de evento obtidos com sucesso.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EventDayPreviewDTO.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<EventDayPreviewDTO>> getEventDays(
            @PathVariable("event_id") Long eventId
    ) {
        var eventDays = eventDayRepository.findByEventId(eventId);
        if (eventDays.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventDays.stream().map(EventDayPreviewDTO::new).toList());
    }

}
