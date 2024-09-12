package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
