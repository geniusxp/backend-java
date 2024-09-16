package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.coupon.CouponDetailsDTO;
import br.com.fiap.sprintjava.dtos.errors.ErrorDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.payment.TicketDetailsDTO;
import br.com.fiap.sprintjava.repositories.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.fiap.sprintjava.dtos.coupon.CreateCouponDTO;
import br.com.fiap.sprintjava.dtos.payment.BuyTicketDTO;
import br.com.fiap.sprintjava.models.Coupon;
import br.com.fiap.sprintjava.models.Ticket;
import br.com.fiap.sprintjava.models.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payments")
@Tag(name = "Pagamentos", description = "Operações relacionadas aos pagamentos do evento.")
public class PaymentController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping("/tickets/buy")
    @Transactional
    @Operation(summary = "Comprar ingresso", description = "Compra um ingresso para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ingresso comprado com sucesso.", content = @Content(schema = @Schema(implementation = Ticket.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Tipo de ingresso não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> buyTicket(
        @RequestBody @Valid BuyTicketDTO buyTicketDTO,
        UriComponentsBuilder builder) {
        val user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var coupon = couponRepository.findByCode(buyTicketDTO.couponCode()).orElse(null);
        var ticketType = ticketTypeRepository.findById(buyTicketDTO.ticketTypeId()).orElse(null);

        if (ticketType == null || ticketType.getFinishedAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Tipo de ingresso não encontrado.", LocalDateTime.now()));
        }

        var ticket = new Ticket(buyTicketDTO, user, ticketType, coupon);
        ticketRepository.save(ticket);

        var uri = builder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(uri).body(new TicketDetailsDTO(ticket));
    }

    @PostMapping("/coupons/{event_id}")
    @Transactional
    @Operation(summary = "Criar cupom", description = "Cria um cupom de desconto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cupom criado com sucesso.", content = @Content(schema = @Schema(implementation = CouponDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Cupom já existe no evento.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> createCoupon(
            @PathVariable("event_id") Long eventId,
            @RequestBody @Valid CreateCouponDTO createCouponDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Bad Request", "Evento não encontrado.", LocalDateTime.now()));
        }

        var existentCoupon = couponRepository.findByCodeAndEventId(createCouponDTO.code(), eventId).orElse(null);
        if (existentCoupon != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Conflict", "Cupom já existe no evento.", LocalDateTime.now()));
        }

        var coupon = new Coupon(createCouponDTO, event);
        couponRepository.save(coupon);

        var uri = builder.path("/coupons/{code}").buildAndExpand(coupon.getId()).toUri();
        return ResponseEntity.created(uri).body(new CouponDetailsDTO(coupon));
    }

    @GetMapping("/coupons/{event_id}/{code}")
    @Operation(summary = "Obter cupom", description = "Obtém um cupom do evento pelo código.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom obtido com sucesso.", content = @Content(schema = @Schema(implementation = CouponDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Cupom não encontrado.", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = "application/json"))
    })
    public ResponseEntity<Object> getCoupon(
            @PathVariable("event_id") Long eventId,
            @PathVariable("code") String code) {
        var coupon = couponRepository.findByCodeAndEventId(code, eventId).orElse(null);
        if(coupon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Not Found", "Cupom não encontrado.", LocalDateTime.now()));
        }

        return ResponseEntity.ok(new CouponDetailsDTO(coupon));
    }
}
