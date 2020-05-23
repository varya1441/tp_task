package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
