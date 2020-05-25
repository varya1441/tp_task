package com.bsu.pt.exam.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Приоритет - студент, отображение “место-приоритет от 0 до 1”
@Entity
@Table(name = "prioriry")
@Data
public class Priority {
    @Id
    private String id;
    @ManyToOne
    private Event event;
    @OneToOne
    private Student student;
    @ManyToOne
    private Result result;


//    private TreeMap<String,Integer> priority=new TreeMap<>();
}
