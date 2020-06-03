package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Событие - название, дата, группа, приоритеты студентов группы, решение
@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(insertable = false, updatable = false)
    private String id;

    @Column(name = "event_name")
    private String eventName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Group group;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "event")
    private List<Priority> priorities = new ArrayList<>();
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Result result;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", date=" + date +
                ", group=" + group.getGroupName() +
                ", priorities=" + priorities.toString() +

                '}';
    }
}
