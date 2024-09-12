package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
