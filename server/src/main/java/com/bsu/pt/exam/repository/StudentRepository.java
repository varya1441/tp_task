package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> getStudentByLogin(String login);

    @Transactional
    void deleteByLogin(String login);
}
