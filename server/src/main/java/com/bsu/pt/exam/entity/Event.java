package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Событие - название, дата, группа, приоритеты студентов группы, решение
@Entity
@Table(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "event_name")
    private String eventName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @ManyToOne
    @JsonIgnore
    private Group group;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "event",
            fetch = FetchType.LAZY)
    private List<Priority> priorities = new ArrayList<>();
    @OneToOne(orphanRemoval = true)
    private Result result;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", date=" + date +
                ", group=" + group.getGroupName() +
                ", priorities=" + priorities +
                ", result=" + result +
                '}';
    }
}
