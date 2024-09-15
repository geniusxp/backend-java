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
@RequestMapping("events/:id/lectures")
@Tag(name = "Palestras", description = "Operações relacionadas às palestras do evento.")
public class LecturesController {
    @GetMapping
    @Operation(summary = "Obter todas as palestras", description = "Obtém todas as palestras do evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestras obtidas com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getLectures() {
        // TODO: obter todos os palestras do evento pelo id
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Criar uma palestra", description = "Cria uma nova palestra para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra criada com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createLecture() {
        // TODO: criar um palestra para o evento pelo id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/:id")
    @Operation(summary = "Obter uma palestra", description = "Obtém uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra obtida com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getLecture() {
        // TODO: obter palestra pelo id
        return ResponseEntity.ok().build();
    }

    @PutMapping("/:id")
    @Operation(summary = "Atualizar uma palestra", description = "Atualiza uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra atualizada com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> updateLecture() {
        // TODO: atualizar o palestra pelo id
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/:id")
    @Operation(summary = "Deletar uma palestra", description = "Deleta uma palestra pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Palestra deletada com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> deleteLecture() {
        // TODO: deletar o palestra pelo id
        return ResponseEntity.ok().build();
    }
}
