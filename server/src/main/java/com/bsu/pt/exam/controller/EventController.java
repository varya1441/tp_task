package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PostMapping(value = "")
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.addEvent(event), HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> getByUserId(@PathVariable String id) {
        return new ResponseEntity<Event>(eventService.getEventById(id), HttpStatus.OK);
    }
}
