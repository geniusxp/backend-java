package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.AdminEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEventRepository extends JpaRepository<AdminEvent, Long> {
}
