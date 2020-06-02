package com.bsu.pt.exam.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EventDto {

    private String eventName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
