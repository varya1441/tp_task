package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.service.EventService;
import com.bsu.pt.exam.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private EventService eventService;
    private ResultService resultService;

    @Autowired
    public EventController(EventService eventService, ResultService resultService) {
        this.eventService = eventService;
        this.resultService = resultService;
    }

    @PostMapping(value = "/{groupName}")
    public ResponseEntity<Event> addEvent(@PathVariable String groupName, @RequestBody EventDto event) {
        return new ResponseEntity<>(eventService.addEvent(groupName, event), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return new ResponseEntity<Event>(eventService.getEventById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{groupName}")
    public ResponseEntity<List<Event>> getAllByGroupName(@PathVariable String groupName) {
        return new ResponseEntity<List<Event>>(eventService.getAllByGroupName(groupName), HttpStatus.OK);
    }

    @GetMapping(value = "/{eventId}/result")
    public ResponseEntity<Result> getResult(@PathVariable String eventId) {

        return new ResponseEntity<Result>(resultService.getResultByEventId(eventId), HttpStatus.OK);
    }

    @PutMapping(value = "/result/{resultId}")
    public ResponseEntity<Result> updateResult(@PathVariable String resultId, @RequestBody Result result) {
        return new ResponseEntity<Result>(resultService.updateResult(resultId, result), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{eventName}/result/{groupName}")
    public ResponseEntity<Result> createResult(@PathVariable String groupName,@PathVariable String eventName) {
        return new ResponseEntity<>(resultService.createResult(eventName,groupName), HttpStatus.CREATED);
    }
}
