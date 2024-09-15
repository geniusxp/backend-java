package br.com.fiap.sprintjava.controllers;


import br.com.fiap.sprintjava.models.Poll;
import br.com.fiap.sprintjava.models.User;
import br.com.fiap.sprintjava.repositories.PollOptionRepository;
import br.com.fiap.sprintjava.repositories.PollRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events/:id/polls")
public class PollsController {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private PollOptionRepository pollOptionRepository;

    @GetMapping
    public ResponseEntity<Page<Poll>> getPolls(
            @PathVariable("id") Long eventId
    ) {
        var polls = pollRepository.findByEvent(eventId);
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/now")
    public ResponseEntity<Poll> getNowPoll(
            @PathVariable("id") Long eventId
    ) {
        var poll = pollRepository.findNowPoll(eventId);

        return ResponseEntity.ok(poll);
    }

    @PostMapping("/:option_id/vote")
    public ResponseEntity<Void> votePoll(
            @PathVariable("option_id") Long pollOptionId
    ) {
        var pollOption = pollOptionRepository.findById(pollOptionId).orElseThrow();

        pollOption.setVotesAmount(pollOption.getVotesAmount() + 1);
        pollOptionRepository.save(pollOption);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/:id/results")
    public ResponseEntity<Poll> getPoll(
            @PathVariable("id") Long pollId
    ) {
        var poll = pollRepository.findById(pollId).orElseThrow();
        return ResponseEntity.ok(poll);
    }
}
