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
@RequestMapping("/events/:id/payments")
@Tag(name = "Pagamentos", description = "Operações relacionadas aos pagamentos do evento.")
public class PaymentController {
    @PostMapping
    @Operation(summary = "Comprar ingresso", description = "Compra um ingresso para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso comprado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> buyTicket() {
        // TODO: comprar ingresso
        return ResponseEntity.ok().build();
    }

    @PostMapping("/coupons")
    @Operation(summary = "Criar cupom", description = "Cria um cupom de desconto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom criado com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> createCoupon() {
        // TODO: criar cupom
        return ResponseEntity.ok().build();
    }

    @GetMapping("/coupons/:coupon")
    @Operation(summary = "Obter cupom", description = "Obtém um cupom pelo código.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom obtido com sucesso.", content = @Content(schema = @Schema(implementation = Object.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Object> getCoupon() {
        // TODO: obter cupom pelo código
        return ResponseEntity.ok().build();
    }
}
