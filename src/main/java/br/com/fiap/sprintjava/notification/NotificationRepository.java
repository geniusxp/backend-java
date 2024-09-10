package br.com.fiap.sprintjava.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Optional<Notification> findByTitle(String title);
}
