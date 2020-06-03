package com.bsu.pt.exam.service;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;

import java.util.List;

public interface EventService {
    Event updateEvent(String eventName, EventDto event);

    Event updateEventResult(Event event);

    Event getEventById(String id);

    List<Event> getAllByGroupName(String groupId);

    Event addEvent(String groupName, EventDto event);

    void deleteEvent(String id);

    Event getEventByName(String name);
}
