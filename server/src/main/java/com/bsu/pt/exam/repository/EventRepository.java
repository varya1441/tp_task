package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    Event getEventsByDate(LocalDate date);

    List<Event> getAllByGroupGroupName(String groupName);

}
