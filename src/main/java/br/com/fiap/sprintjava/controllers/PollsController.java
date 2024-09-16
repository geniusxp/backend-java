package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ErrorDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.sprintjava.models.Poll;
import br.com.fiap.sprintjava.repositories.PollOptionRepository;
import br.com.fiap.sprintjava.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events/{id}/polls")
@Tag(name = "Enquetes", description = "Operações relacionadas às enquetes do evento.")
public class PollsController {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private PollOptionRepository pollOptionRepository;

    @GetMapping
    @Operation(summary = "Obter todas as enquetes", description = "Obtém todas as enquetes do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enquetes obtidas com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Page<Poll>> getPolls(
            @PathVariable("id") Long eventId,
            Pageable pageable
    ) {
        var polls = pollRepository.findByEvent(eventId, pageable);
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/now")
    @Operation(summary = "Obter enquete atual", description = "Obtém a enquete atual do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enquete atual obtida com sucesso.", content = @Content(schema = @Schema(implementation = Poll.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "Nenhuma enquete acontecendo no momento.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Poll> getNowPoll(
            @PathVariable("id") Long eventId
    ) {
        var poll = pollRepository.findNowPoll(eventId);
        if(poll == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(poll);
    }

    @PutMapping("/{option_id}/vote")
    @Operation(summary = "Votar na enquete", description = "Vota na enquete pelo id da opção.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Voto computado com sucesso.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Opção da enquete não encontrada.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> votePoll(
            @PathVariable("option_id") Long pollOptionId
    ) {
        var pollOption = pollOptionRepository.findById(pollOptionId).orElse(null);
        if(pollOption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Opção da enquete não encontrada.", LocalDateTime.now()));
        }

        pollOption.setVotesAmount(pollOption.getVotesAmount() + 1);
        pollOptionRepository.save(pollOption);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{poll_id}/results")
    @Operation(summary = "Obter resultado da enquete", description = "Obtém o resultado da enquete pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado da enquete obtido com sucesso.", content = @Content(schema = @Schema(implementation = Poll.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Enquete não encontrada.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> getPoll(
            @PathVariable("poll_id") Long pollId
    ) {
        var poll = pollRepository.findById(pollId).orElse(null);
        if(poll == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Not Found", "Enquete não encontrada.", LocalDateTime.now()));
        }

        return ResponseEntity.ok(poll);
    }
}
