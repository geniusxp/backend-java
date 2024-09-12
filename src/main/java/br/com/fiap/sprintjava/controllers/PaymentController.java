package br.com.fiap.sprintjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/:id/payments")
public class PaymentController {
    @PostMapping
    public ResponseEntity<Object> buyTicket() {
        // TODO: comprar ingresso
        return ResponseEntity.ok().build();
    }

    @PostMapping("/coupons")
    public ResponseEntity<Object> createCoupon() {
        // TODO: criar cupom
        return ResponseEntity.ok().build();
    }

    @GetMapping("/coupons/:coupon")
    public ResponseEntity<Object> getCoupon() {
        // TODO: obter cupom pelo código
        return ResponseEntity.ok().build();
    }
}
