package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Page<Lecture> findByEventDay(Long eventDay, Pageable pageable);
}
