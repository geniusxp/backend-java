package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}
