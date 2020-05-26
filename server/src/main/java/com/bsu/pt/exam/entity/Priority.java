package com.bsu.pt.exam.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private int priority;

}
