package com.bsu.pt.exam.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Решение - порядок студентов
@Entity
@Table(name = "result")
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   // @NotEmpty
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "result",
            fetch = FetchType.EAGER
    )
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }
}
