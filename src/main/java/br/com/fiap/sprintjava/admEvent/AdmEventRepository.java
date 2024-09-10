package br.com.fiap.sprintjava.admEvent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmEventRepository extends JpaRepository<AdmEvent, Long> {
    Optional<AdmEvent> findByEventName(String eventName);
}
