package br.com.fiap.sprintjava.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/:id/polls")
public class PollsController {
    @GetMapping
    public ResponseEntity<Object> getPolls() {
        // TODO: obter todas as enquetes do evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/now")
    public ResponseEntity<Object> getNowPoll() {
        // TODO: obter enquete atual do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping("/:id/vote")
    public ResponseEntity<Object> votePoll() {
        // TODO: votar na enquete pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id/results")
    public ResponseEntity<Object> getPoll() {
        // TODO: obter resultado da enquete pelo id
        return ResponseEntity.ok().build();
    }
}
