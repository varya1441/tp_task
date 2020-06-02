package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Решение - порядок студентов
@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    // @NotEmpty
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "result",
            fetch = FetchType.LAZY
    )
    private List<Student> students = new ArrayList<>();
    @OneToOne(orphanRemoval = true)
    private Event event;

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", students=" + students +
                ", event=" + event.getEventName() +
                '}';
    }
}
