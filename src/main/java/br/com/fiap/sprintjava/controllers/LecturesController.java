package br.com.fiap.sprintjava.controllers;


import br.com.fiap.sprintjava.dtos.lecture.CreateLectureDTO;
import br.com.fiap.sprintjava.dtos.lecture.UpdateLectureDTO;
import br.com.fiap.sprintjava.models.EventDay;
import br.com.fiap.sprintjava.models.Lecture;
import br.com.fiap.sprintjava.repositories.EventDayRepository;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.repositories.LectureRepository;
import br.com.fiap.sprintjava.repositories.SpeakerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("events/:id/lectures")
public class LecturesController {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDayRepository eventDayRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public ResponseEntity<Page<Lecture>> getLectures(
            @PathVariable("id") Long eventId,
            Pageable pageable
    ) {
        var lectures = lectureRepository.findByEvent(eventId, pageable);
        return ResponseEntity.ok(lectures);
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(
            @RequestBody @Valid CreateLectureDTO lectureDTO,
            UriComponentsBuilder builder
    ) {
        var eventDay = eventDayRepository.findById(lectureDTO.eventDayId()).orElseThrow();
        var speaker = speakerRepository.findById(lectureDTO.speakerId()).orElseThrow();
        var lecture = new Lecture(
                lectureDTO.name(),
                lectureDTO.description(),
                lectureDTO.date(),
                eventDay,
                speaker
        );

        lectureRepository.save(lecture);

        var uri = builder.path("/lectures/{id}").buildAndExpand(lecture.getId()).toUri();
        return ResponseEntity.created(uri).body(lecture);
    }

    @GetMapping("/:id")
    public ResponseEntity<Lecture> getLecture(
            @PathVariable("id") Long lectureId
    ) {
        var lecture = lectureRepository.findById(lectureId).orElseThrow();

        return ResponseEntity.ok(lecture);
    }

    @PutMapping("/:id")
    public ResponseEntity<Lecture> updateLecture(
            @PathVariable("id") Long lectureId,
            @RequestBody @Valid UpdateLectureDTO lectureDTO
    ) {
        var lecture = lectureRepository.findById(lectureId).orElseThrow();

        lecture.update(lectureDTO);
        lectureRepository.save(lecture);

        return ResponseEntity.ok(lecture);
    }

    @DeleteMapping("/:id")
    public ResponseEntity<Void> deleteLecture(
            @PathVariable("id") Long lectureId
    ) {
        lectureRepository.deleteById(lectureId);
        return ResponseEntity.ok().build();
    }
}
