package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.EventDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDayRepository extends JpaRepository<EventDay, Long> {
}
