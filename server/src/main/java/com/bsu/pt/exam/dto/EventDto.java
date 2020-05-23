package com.bsu.pt.exam.dto;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Result;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDto {
     private long id;
    private String eventName;
    private LocalDate date;
    private Group group;

    private List<Priority> priorities = new ArrayList<>();
    private Result result;
}
