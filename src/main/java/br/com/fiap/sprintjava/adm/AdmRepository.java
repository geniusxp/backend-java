package br.com.fiap.sprintjava.adm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmRepository extends JpaRepository<Adm, Long> {
    Optional<Adm> findByNome(String nome);
}
