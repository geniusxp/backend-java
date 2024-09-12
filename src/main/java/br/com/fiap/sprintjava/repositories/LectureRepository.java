package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
