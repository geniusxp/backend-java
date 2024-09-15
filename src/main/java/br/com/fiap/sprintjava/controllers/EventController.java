package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.event.CreateEventDTO;
import br.com.fiap.sprintjava.dtos.event.EventDetailsDTO;
import br.com.fiap.sprintjava.dtos.event.UpdateEventDTO;
import br.com.fiap.sprintjava.models.Event;
import br.com.fiap.sprintjava.repositories.EventRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<List<EventDetailsDTO>> getEvents() {
        var lista = eventRepository.findAll().stream().map(EventDetailsDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<EventDetailsDTO> createEvent(@RequestBody @Valid CreateEventDTO dto,
                                                       UriComponentsBuilder builder) {
        var event = new Event(dto);
        eventRepository.save(event);
        var url = builder.path("events/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(url).body(new EventDetailsDTO(event));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailsDTO> getEvent(@PathVariable("id") Long id) {
        var event = eventRepository.getReferenceById(id);
        return ResponseEntity.ok(new EventDetailsDTO(event));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EventDetailsDTO> updateEvent(@PathVariable("id") Long id, @RequestBody @Valid UpdateEventDTO dto) {
        var event = eventRepository.getReferenceById(id);
        event.atualizar(dto);
        return ResponseEntity.ok(new EventDetailsDTO(event));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tickets")
    public ResponseEntity<Object> createTicketType() {
        // TODO: criar tipo de ingresso
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/days")
    public ResponseEntity<Object> createEventDay() {
        // TODO: criar dia de evento
        return ResponseEntity.ok().build();
    }
}
