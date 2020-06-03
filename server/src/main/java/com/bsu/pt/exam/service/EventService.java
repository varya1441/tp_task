package com.bsu.pt.exam.service;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Result;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    Event updateEvent(String eventId, EventDto event);
    Event updateEventResult(Event event);

    Event getEventById(String id);

    List<Event> getAllByGroupName(String groupId);

    Event getEventByDate(LocalDate date);


    Result getEventResult(String id);

    Event addEvent(String groupName, EventDto event);

    void deleteEvent(String id);
    Event getEventByName(String name);
}
