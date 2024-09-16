package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.ticket.TicketDetailsDTO;
import br.com.fiap.sprintjava.dtos.ticket.UpdateTicketDTO;
import br.com.fiap.sprintjava.repositories.TicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@Tag(name = "Ingressos", description = "Operações relacionadas aos ingressos.")
public class TicketsController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/me")
    @Operation(summary = "Obter meus ingressos", description = "Obtém todos os ingressos do usuário logado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingressos obtidos com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<List<TicketDetailsDTO>> getTickets() {
        var lista = ticketRepository.findAll().stream().map(TicketDetailsDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar um ingresso", description = "Atualiza um ingresso pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<TicketDetailsDTO> scanTicket(@PathVariable("id") Long id,
                                                       @RequestBody @Valid UpdateTicketDTO dto) {
        var ticket = ticketRepository.getReferenceById(id);
        ticket.update(dto);
        return ResponseEntity.ok(new TicketDetailsDTO(ticket));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um ingresso", description = "Obtém um ingresso pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getTicket(@PathVariable("id") Long id) {
        var ticket = ticketRepository.getReferenceById(id);
        return ResponseEntity.ok(new TicketDetailsDTO(ticket));
    }
}
