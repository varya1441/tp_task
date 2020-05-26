package com.bsu.pt.exam.entity;

import lombok.Data;

import javax.persistence.*;

//Приоритет - студент, отображение “место-приоритет от 0 до 1”
@Entity
@Table(name = "priority")
@Data
public class Priority {
    @Id
    private String id;
    @ManyToOne
    private Event event;
    @OneToOne
    private Student student;


}
