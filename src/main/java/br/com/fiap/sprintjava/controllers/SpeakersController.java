package br.com.fiap.sprintjava.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events/:id/speakers")
public class SpeakersController {
    @GetMapping
    public ResponseEntity<Object> getSpeakers() {
        // TODO: obter todos os palestrantes do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> createSpeaker() {
        // TODO: criar um palestrante para o evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    public ResponseEntity<Object> getSpeaker() {
        // TODO: obter palestrante pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    public ResponseEntity<Object> updateSpeaker() {
        // TODO: atualizar o palestrante pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    public ResponseEntity<Object> deleteSpeaker() {
        // TODO: deletar o palestrante pelo id
        return ResponseEntity.ok().build();
    }
}
