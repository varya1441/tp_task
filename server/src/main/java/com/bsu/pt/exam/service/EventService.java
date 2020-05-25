package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.exception.ItemNotFoundException;

import java.time.LocalDate;

public interface EventService {
    Event update(String id, Event event);

    Event getEventById(String id);

    Event getEventByDate(LocalDate date);

    Result getEventResult(String id);
    Event addEvent(Event event) throws ItemNotFoundException;
}
