package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
