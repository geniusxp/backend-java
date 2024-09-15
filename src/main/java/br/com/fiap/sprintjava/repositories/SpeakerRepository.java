package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Speaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    Page<Speaker> findByEvent(Long eventId, Pageable pageable);
}
