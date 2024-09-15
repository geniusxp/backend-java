package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@Tag(name = "Ingressos", description = "Operações relacionadas aos ingressos.")
public class TicketsController {
    @GetMapping
    @Operation(summary = "Obter todos os ingressos", description = "Obtém todos os ingressos do usuário logado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingressos obtidos com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getTickets() {
        // TODO: obter todos os ingressos do usuário logado
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    @Operation(summary = "Atualizar um ingresso", description = "Atualiza um ingresso pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> scanTicket() {
        // TODO: atualiza o dateOfUse (para o dia atual) do ingresso pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter um ingresso", description = "Obtém um ingresso pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getTicket() {
        // TODO: obter ingresso pelo id
        return ResponseEntity.ok().build();
    }
}
