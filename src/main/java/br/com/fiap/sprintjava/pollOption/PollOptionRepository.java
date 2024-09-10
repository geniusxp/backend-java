package br.com.fiap.sprintjava.pollOption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
    Optional<PollOption> findById(Long id);
}
