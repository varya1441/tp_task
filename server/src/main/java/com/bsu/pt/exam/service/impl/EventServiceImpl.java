package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Group;
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
    public Event addEvent(EventDto eventDto) throws ItemNotFoundException {
        Event event = new Event();
        Group group = groupService.getGroup(eventDto.getGroupName());
        event.setDate(eventDto.getDate());
        event.setGroup(group);
        return save(event);
    }

    public Event save(Event event) {
        if (event != null) {
            return eventRepository.save(event);
        } else {
            throw new ItemNotFoundException("There is no motivation to save");
        }
    }

    @Override
    public Event update(String id, Event event) {
        return null;
    }

    @Override
    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("event with id - " + id + "not found"));
    }

    @Override
    public Event getEventByDate(LocalDate date) {
        return null;
    }

    @Override
    public Result getEventResult(String id) {
        return null;
    }
}
