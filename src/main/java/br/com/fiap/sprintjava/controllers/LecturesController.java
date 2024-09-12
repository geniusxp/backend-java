package br.com.fiap.sprintjava.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events/:id/lectures")
public class LecturesController {
    @GetMapping
    public ResponseEntity<Object> getLectures() {
        // TODO: obter todos os palestras do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> createLecture() {
        // TODO: criar um palestra para o evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    public ResponseEntity<Object> getLecture() {
        // TODO: obter palestra pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    public ResponseEntity<Object> updateLecture() {
        // TODO: atualizar o palestra pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    public ResponseEntity<Object> deleteLecture() {
        // TODO: deletar o palestra pelo id
        return ResponseEntity.ok().build();
    }
}
