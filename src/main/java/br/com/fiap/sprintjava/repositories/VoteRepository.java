package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
