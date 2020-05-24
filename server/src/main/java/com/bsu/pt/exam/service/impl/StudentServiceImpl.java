package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.StudentRepository;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        List<Student> users = studentRepository.findAll();
        if (users.size() <= 0) {
            throw new ItemNotFoundException("No users found");
        }
        return users;
    }

    @Override
    public Student save(Student user) {
        return null;
    }

    @Override
    public Student update(String id, Student pUser) {
        return null;
    }

    @Override
    public void resetPassword(Student user, String newPassword) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Student findByLogin(String login) {
        return   studentRepository.getStudentByLogin(login)
                .orElseThrow(() -> new ItemNotFoundException("student with name - " + login + "not found"));
    }

    @Override
    public Student findById(long id) {
        return  studentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("student with id - " + id + "not found"));
    }

}
