package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
