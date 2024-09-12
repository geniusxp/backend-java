package br.com.fiap.sprintjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @GetMapping
    public ResponseEntity<Object> getEvents() {
        // TODO: obter todos os eventos
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> createEvent() {
        // TODO: criar um evento
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    public ResponseEntity<Object> getEvent() {
        // TODO: obter evento pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    public ResponseEntity<Object> updateEvent() {
        // TODO: atualizar o evento pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    public ResponseEntity<Object> deleteEvent() {
        // TODO: deletar o evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tickets")
    public ResponseEntity<Object> createTicketType() {
        // TODO: criar tipo de ingresso
        return ResponseEntity.ok().build();
    }
}
