package br.com.fiap.sprintjava.speaker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    Optional<Speaker> findByEmail(String email);
}
