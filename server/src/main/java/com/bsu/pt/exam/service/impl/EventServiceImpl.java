package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.EventRepository;
import com.bsu.pt.exam.service.EventService;
import com.bsu.pt.exam.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;
    GroupService groupService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, GroupServiceImpl groupService) {
        this.eventRepository = eventRepository;
        this.groupService = groupService;
    }

    @Override
    public List<Event> getAllByGroupName(String groupName) {
        List<Event> events = eventRepository.getAllByGroupGroupName(groupName);
        return Objects.requireNonNullElseGet(events, ArrayList::new);
    }

    @Override
    public Event addEvent(String groupName, EventDto eventDto) {
        Event event = new Event();
        event.setDate(eventDto.getDate());
        event.setEventName(eventDto.getEventName());
        try {
            Group group = groupService.getGroup(groupName);
            event.setGroup(group);

        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException("There is no group" + "with name" + groupName);
        }
        return save(event);
    }

    public Event save(Event event) {
        if (event != null) {
            return eventRepository.save(event);
        } else {
            throw new ItemNotFoundException("There is no event to save");
        }
    }

    @Override
    public Event getEventByName(String name) {
        return eventRepository.getEventsByEventName(name)
                .orElseThrow(() -> new ItemNotFoundException("event with name - " + name + "not found"));

    }

    @Override
    public Event updateEvent(String eventId, EventDto event) {
        Event event1 = getEventById(eventId);
        event1.setEventName(event.getEventName());
        event1.setDate(event.getDate());
        return eventRepository.save(event1);
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("event with id - " + id + "not found"));
    }

    @Override
    public Event getEventByDate(LocalDate date) {
        return eventRepository.getEventsByDate(date);
    }

    @Override
    public Result getEventResult(String id) {
        return getEventById(id).getResult();
    }

    @Override
    public void deleteEvent(String id) {
        Event eventToRemove = getEventById(id);
        if (eventToRemove != null) {
            eventRepository.delete(eventToRemove);
        } else {
            throw new ItemNotFoundException("No event to delete found with id" + id);
        }
    }

    @Override
    public Event updateEventResult(Event event) {
        Event event1 = getEventById(event.getId());
        event1.setResult(event.getResult());
        return save(event);
    }


}
