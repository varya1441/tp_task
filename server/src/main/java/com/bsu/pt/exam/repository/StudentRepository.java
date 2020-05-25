package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> getStudentByLogin(String login);
}
