package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
