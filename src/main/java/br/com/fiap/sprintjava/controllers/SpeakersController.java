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
@RequestMapping("events/:id/speakers")
@Tag(name = "Palestrantes", description = "Operações relacionadas aos palestrantes do evento.")
public class SpeakersController {
    @GetMapping
    @Operation(summary = "Obter todos os palestrantes", description = "Obtém todos os palestrantes do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrantes obtidos com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getSpeakers() {
        // TODO: obter todos os palestrantes do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Criar um palestrante", description = "Cria um novo palestrante para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createSpeaker() {
        // TODO: criar um palestrante para o evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter um palestrante", description = "Obtém um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getSpeaker() {
        // TODO: obter palestrante pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    @Operation(summary = "Atualizar um palestrante", description = "Atualiza um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> updateSpeaker() {
        // TODO: atualizar o palestrante pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    @Operation(summary = "Deletar um palestrante", description = "Deleta um palestrante pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestrante deletado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> deleteSpeaker() {
        // TODO: deletar o palestrante pelo id
        return ResponseEntity.ok().build();
    }
}
