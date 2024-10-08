package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    Optional<Coupon> findByCodeAndEventId(String code, Long eventId);
}
