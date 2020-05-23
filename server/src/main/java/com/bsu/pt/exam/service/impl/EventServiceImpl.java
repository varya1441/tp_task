package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.EventRepository;
import com.bsu.pt.exam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event addEvent(Event event) throws ItemNotFoundException {
        if (event != null) {
            return eventRepository.save(event);
        } else {
            throw new ItemNotFoundException("There is no motivation to save");
        }
    }

    @Override
    public Event update(long id, Event event) {
        return null;
    }

    @Override
    public Event getEventById(long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public Event getEventByDate(LocalDate date) {
        return null;
    }

    @Override
    public Result getEventResult(long id) {
        return null;
    }
}
