package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByEventDayId(Long eventDayId);
}
