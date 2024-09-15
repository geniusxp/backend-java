package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.coupon.CreateCouponDTO;
import br.com.fiap.sprintjava.dtos.payment.BuyTicketDTO;
import br.com.fiap.sprintjava.models.Coupon;
import br.com.fiap.sprintjava.models.Payment;
import br.com.fiap.sprintjava.models.Ticket;
import br.com.fiap.sprintjava.models.User;
import br.com.fiap.sprintjava.repositories.CouponRepository;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.repositories.PaymentRepository;
import br.com.fiap.sprintjava.repositories.TicketTypeRepository;
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
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private CouponRepository couponRepository;

    @PostMapping("/buy")
    @Transactional
    public ResponseEntity<Ticket> buyTicket(
            @RequestBody @Valid BuyTicketDTO buyTicketDTO,
            UriComponentsBuilder builder) {
        val user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var coupon = couponRepository.findByCode(buyTicketDTO.couponCode()).orElse(null);
        var ticketType = ticketTypeRepository.findById(buyTicketDTO.ticketType()).orElseThrow();

        var payment = new Payment(buyTicketDTO.paymentMethod(), ticketType.getPriceValue());
        paymentRepository.save(payment);

        var ticket = new Ticket(buyTicketDTO, user, ticketType, payment, coupon);

        var uri = builder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
        return ResponseEntity.created(uri).body(ticket);
    }

    @PostMapping("/coupons")
    @Transactional
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
    public ResponseEntity<Coupon> getCoupon(@PathVariable("code") String code) {
        var coupon = couponRepository.findByCode(code).orElseThrow();

        return ResponseEntity.ok(coupon);
    }
}
