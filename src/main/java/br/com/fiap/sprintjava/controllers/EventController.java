package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.event.CreateEventDTO;
import br.com.fiap.sprintjava.dtos.event.EventDetailsDTO;
import br.com.fiap.sprintjava.dtos.event.UpdateEventDTO;
import br.com.fiap.sprintjava.dtos.tickettype.CreateTicketTypeDTO;
import br.com.fiap.sprintjava.dtos.tickettype.TicketTypeDetailsDTO;
import br.com.fiap.sprintjava.models.Event;
import br.com.fiap.sprintjava.models.TicketType;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.repositories.TicketTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/events")
@Tag(name = "Eventos", description = "Operações relacionadas aos eventos.")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @GetMapping
    @Operation(summary = "Obter todos os eventos", description = "Obtém todos os eventos cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Eventos obtidos com sucesso.", content = @Content(schema = @Schema(implementation = EventDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<EventDetailsDTO>> getEvents() {
        var lista = eventRepository.findAll().stream().map(EventDetailsDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um evento", description = "Cria um novo evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso.", content = @Content(schema = @Schema(implementation = EventDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<EventDetailsDTO> createEvent(@RequestBody @Valid CreateEventDTO dto,
                                                       UriComponentsBuilder builder) {
        var event = new Event(dto);
        eventRepository.save(event);
        var url = builder.path("events/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(url).body(new EventDetailsDTO(event));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um evento", description = "Obtém um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento obtido com sucesso.", content = @Content(schema = @Schema(implementation = EventDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<EventDetailsDTO> getEvent(@PathVariable("id") Long id) {
        var event = eventRepository.getReferenceById(id);
        return ResponseEntity.ok(new EventDetailsDTO(event));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar um evento", description = "Atualiza um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso.", content = @Content(schema = @Schema(implementation = EventDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<EventDetailsDTO> updateEvent(@PathVariable("id") Long id, @RequestBody @Valid UpdateEventDTO dto) {
        var event = eventRepository.getReferenceById(id);
        event.atualizar(dto);
        return ResponseEntity.ok(new EventDetailsDTO(event));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar um evento", description = "Deleta um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento deletado com sucesso.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tickets-type")
    @Transactional
    @Operation(summary = "Criar um tipo de ingresso", description = "Cria um novo tipo de ingresso.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de ingresso criado com sucesso.", content = @Content(schema = @Schema(implementation = TicketType.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<TicketTypeDetailsDTO> createTicketType(
            @PathVariable("id") Long eventId,
            @RequestBody @Valid CreateTicketTypeDTO ticketType,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.getReferenceById(eventId);
        var newTicketType = new TicketType(ticketType, event);

        ticketTypeRepository.save(newTicketType);

        var url = builder.path("events/tickets-type/{id}").buildAndExpand(newTicketType.getId()).toUri();
        return ResponseEntity.created(url).body(new TicketTypeDetailsDTO(newTicketType));
    }

    @PostMapping("/days")
    @Transactional
    @Operation(summary = "Criar um dia de evento", description = "Cria um novo dia de evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dia de evento criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createEventDay() {
        // TODO: criar dia de evento
        return ResponseEntity.ok().build();
    }
}
