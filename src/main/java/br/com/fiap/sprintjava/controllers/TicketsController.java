package br.com.fiap.sprintjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    @GetMapping
    public ResponseEntity<Object> getTickets() {
        // TODO: obter todos os ingressos do usuário logado
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    public ResponseEntity<Object> scanTicket() {
        // TODO: atualiza o dateOfUse (para o dia atual) do ingresso pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    public ResponseEntity<Object> getTicket() {
        // TODO: obter ingresso pelo id
        return ResponseEntity.ok().build();
    }
}
