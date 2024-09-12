package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
