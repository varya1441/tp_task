package com.bsu.pt.exam.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Событие - название, дата, группа, приоритеты студентов группы, решение
@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "event_name")
    private String eventName;
    private LocalDate date;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Group group;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "event",
            fetch = FetchType.EAGER)
    private List<Priority> priorities = new ArrayList<>();
    @OneToOne
    private Result result;

}
