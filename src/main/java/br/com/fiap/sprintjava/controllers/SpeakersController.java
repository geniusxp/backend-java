package br.com.fiap.sprintjava.controllers;


import br.com.fiap.sprintjava.dtos.speaker.CreateSpeakerDTO;
import br.com.fiap.sprintjava.dtos.speaker.UpdateSpeakerDTO;
import br.com.fiap.sprintjava.models.Speaker;
import br.com.fiap.sprintjava.repositories.EventRepository;
import br.com.fiap.sprintjava.repositories.SpeakerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("events/:id/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<Page<Speaker>> getSpeakers(
            @PathVariable("id") Long eventId,
            Pageable pageable
    ) {
        var speakers = speakerRepository.findByEvent(eventId, pageable);
        return ResponseEntity.ok(speakers);
    }

    @PostMapping
    public ResponseEntity<Speaker> createSpeaker(
            @PathVariable("id") Long eventId,
            @RequestBody @Valid CreateSpeakerDTO speakerDTO,
            UriComponentsBuilder builder
    ) {
        var event = eventRepository.findById(eventId).orElseThrow();
        var speaker = new Speaker(
                speakerDTO.name(),
                speakerDTO.description(),
                speakerDTO.socialMediaUrl(),
                speakerDTO.language(),
                speakerDTO.avatarUrl(),
                event
        );

        speakerRepository.save(speaker);

        var uri = builder.path("/speakers/{id}").buildAndExpand(speaker.getId()).toUri();
        return ResponseEntity.created(uri).body(speaker);
    }

    @GetMapping("/:id")
    public ResponseEntity<Speaker> getSpeaker(
            @PathVariable("id") Long speakerId
    ) {
        var speaker = speakerRepository.findById(speakerId).orElseThrow();
        return ResponseEntity.ok(speaker);
    }

    @PutMapping("/:id")
    public ResponseEntity<Speaker> updateSpeaker(
            @PathVariable("id") Long speakerId,
            @RequestBody @Valid UpdateSpeakerDTO speakerDTO
    ) {
        var speaker = speakerRepository.findById(speakerId).orElseThrow();
        speaker.update(speakerDTO);

        speakerRepository.save(speaker);
        return ResponseEntity.ok(speaker);
    }

    @DeleteMapping("/:id")
    public ResponseEntity<Void> deleteSpeaker(
            @PathVariable("id") Long speakerId
    ) {
        speakerRepository.deleteById(speakerId);
        return ResponseEntity.ok().build();
    }
}
