package com.bsu.pt.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

//Приоритет - студент, отображение “место-приоритет от 0 до 1”
@Entity
@Table(name = "priority")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Priority {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Event event;
    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "priority_id"))
    Map<Integer, Integer> priorities = new TreeMap<>();

    @Override
    public String toString() {
        return "Priority{" +
                "id='" + id + '\'' +
                ", event=" + event +
                ", student=" + student.getLogin() +
                ", priorities=" + priorities.toString() +
                '}';
    }
}
