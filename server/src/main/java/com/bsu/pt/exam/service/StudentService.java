package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Student;

public interface StudentService {
 Student getStudent(long id);
 Student updateStudent(long id);
 Student authStudent();
}
