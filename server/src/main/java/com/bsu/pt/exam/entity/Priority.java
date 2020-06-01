package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

//Приоритет - студент, отображение “место-приоритет от 0 до 1”
@Entity
@Table(name = "priority")
@Data
public class Priority {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Event event;
    @OneToOne(orphanRemoval = true)
    private Student student;
    @ElementCollection
    Map<Integer, Integer> priorities = new TreeMap<>();

}
