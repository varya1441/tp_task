package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Student;

import java.util.List;

public interface StudentService {
    Student findByLogin(String login);

    Student findById(String id);

    List<Student> getAll();


    Student save(Student user);

    Student update(String id, Student pUser);

    void delete(String id);

}
