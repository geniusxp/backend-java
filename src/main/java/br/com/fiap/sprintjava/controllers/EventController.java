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
@RequestMapping("/events")
@Tag(name = "Eventos", description = "Operações relacionadas aos eventos.")
public class EventController {
    @GetMapping
    @Operation(summary = "Obter todos os eventos", description = "Obtém todos os eventos cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Eventos obtidos com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getEvents() {
        // TODO: obter todos os eventos
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Criar um evento", description = "Cria um novo evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createEvent() {
        // TODO: criar um evento
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter um evento", description = "Obtém um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getEvent() {
        // TODO: obter evento pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    @Operation(summary = "Atualizar um evento", description = "Atualiza um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> updateEvent() {
        // TODO: atualizar o evento pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    @Operation(summary = "Deletar um evento", description = "Deleta um evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento deletado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> deleteEvent() {
        // TODO: deletar o evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tickets-type")
    @Operation(summary = "Criar um tipo de ingresso", description = "Cria um novo tipo de ingresso.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de ingresso criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createTicketType() {
        // TODO: criar tipo de ingresso
        return ResponseEntity.ok().build();
    }
}
