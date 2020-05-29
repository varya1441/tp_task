package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
    private LocalDate date;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    private Group group;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "event",
            fetch = FetchType.EAGER)
    private List<Priority> priorities = new ArrayList<>();
    @OneToOne
    private Result result;

}
