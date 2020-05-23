package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.exception.ItemNotFoundException;

import java.time.LocalDate;

public interface EventService {
    Event update(long id, Event event);

    Event getEventById(long id);

    Event getEventByDate(LocalDate date);

    Result getEventResult(long id);
    Event addEvent(Event event) throws ItemNotFoundException;
}
