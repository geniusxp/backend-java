package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Poll;
import br.com.fiap.sprintjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Page<Poll> findByEvent(Long eventId);

    @Query("SELECT p FROM Poll p WHERE p.event.id = :eventId AND p.startDate < CURRENT_TIMESTAMP AND p.endDate > CURRENT_TIMESTAMP")
    Poll findNowPoll(Long eventId);
}
