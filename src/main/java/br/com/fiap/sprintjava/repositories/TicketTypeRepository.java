package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
