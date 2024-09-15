package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/:id/polls")
@Tag(name = "Enquetes", description = "Operações relacionadas às enquetes do evento.")
public class PollsController {
    @GetMapping
    @Operation(summary = "Obter todas as enquetes", description = "Obtém todas as enquetes do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enquetes obtidas com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getPolls() {
        // TODO: obter todas as enquetes do evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/now")
    @Operation(summary = "Obter enquete atual", description = "Obtém a enquete atual do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enquete atual obtida com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getNowPoll() {
        // TODO: obter enquete atual do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping("/:id/vote")
    @Operation(summary = "Votar na enquete", description = "Vota na enquete pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Voto computado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> votePoll() {
        // TODO: votar na enquete pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id/results")
    @Operation(summary = "Obter resultado da enquete", description = "Obtém o resultado da enquete pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado da enquete obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getPoll() {
        // TODO: obter resultado da enquete pelo id
        return ResponseEntity.ok().build();
    }
}
