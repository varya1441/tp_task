package com.bsu.pt.exam.service;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.exception.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event update(String id, Event event);

    Event getEventById(String id);

    List<Event> getAllByGroupName(String groupId);

    Event getEventByDate(LocalDate date);

    Result getEventResult(String id);

    Event addEvent(EventDto event) throws ItemNotFoundException;
}
