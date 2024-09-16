package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    List<Speaker> findByEventId(Long eventId);
}
