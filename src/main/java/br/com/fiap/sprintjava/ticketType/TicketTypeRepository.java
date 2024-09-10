package br.com.fiap.sprintjava.ticketType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Optional<TicketType> findById(Long id);
}
