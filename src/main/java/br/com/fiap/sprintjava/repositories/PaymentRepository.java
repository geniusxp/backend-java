package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
