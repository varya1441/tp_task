package com.bsu.pt.exam.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Приоритет - студент, отображение “место-приоритет от 0 до 1”
@Entity
@Table(name = "priorirty")
@Getter
@Setter
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Event event;
    @OneToOne
    private Student student;


//    private TreeMap<String,Integer> priority=new TreeMap<>();
}
