package br.com.fiap.sprintjava.poll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> findByTitle(String title);
}
