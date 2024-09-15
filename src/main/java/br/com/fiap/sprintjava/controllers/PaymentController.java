package br.com.fiap.sprintjava.controllers;

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
import br.com.fiap.sprintjava.models.Payment;
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

@RestController
@RequestMapping("/events/:id/payments")
@Tag(name = "Pagamentos", description = "Operações relacionadas aos pagamentos do evento.")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping("/buy")
    @Transactional
    @Operation(summary = "Comprar ingresso", description = "Compra um ingresso para o evento pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingresso comprado com sucesso.", content = @Content(schema = @Schema(implementation = Ticket.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<TicketDetailsDTO> buyTicket(
            @RequestBody @Valid BuyTicketDTO buyTicketDTO,
            UriComponentsBuilder builder) {
        val user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var coupon = couponRepository.findByCode(buyTicketDTO.couponCode()).orElse(null);
        var ticketType = ticketTypeRepository.findById(buyTicketDTO.ticketType()).orElseThrow();

        var payment = new Payment(buyTicketDTO.paymentMethod(), ticketType.getPriceValue());
        paymentRepository.save(payment);

        var ticket = new Ticket(user, ticketType, payment, coupon);
        ticketRepository.save(ticket);

        var uri = builder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(uri).body(new TicketDetailsDTO(ticket));
    }

    @PostMapping("/coupons")
    @Transactional
    @Operation(summary = "Criar cupom", description = "Cria um cupom de desconto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom criado com sucesso.", content = @Content(schema = @Schema(implementation = Coupon.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Coupon> createCoupon(
            @PathVariable("id") Long eventId,
            @RequestBody @Valid CreateCouponDTO createCouponDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.findById(eventId).orElseThrow();
        var coupon = new Coupon(createCouponDTO, event);

        couponRepository.save(coupon);

        var uri = builder.path("/coupons/{coupon}").buildAndExpand(coupon.getId()).toUri();
        return ResponseEntity.created(uri).body(coupon);
    }

    @GetMapping("/coupons/:code")
    @Operation(summary = "Obter cupom", description = "Obtém um cupom pelo código.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom obtido com sucesso.", content = @Content(schema = @Schema(implementation = Coupon.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Coupon> getCoupon(@PathVariable("code") String code) {
        var coupon = couponRepository.findByCode(code).orElseThrow();

        return ResponseEntity.ok(coupon);
    }
}
