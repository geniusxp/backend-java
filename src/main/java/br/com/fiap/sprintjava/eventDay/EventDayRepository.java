package br.com.fiap.sprintjava.eventDay;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventDayRepository extends JpaRepository<EventDay, Long> {
    Optional<EventDay> findByEventId(long eventId);
}
