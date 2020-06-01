package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.StudentRepository;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Student setPriority(String studentId, PriorityDto priorityDto) {
        Student student = findById(studentId);
        Priority p = student.getPriority();
        p.setPriorities(priorityDto.getPriority());
        return student;
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
    public Student save(Student pUser) {

        String password = pUser.getPassword() != null ? pUser.getPassword() : Student.DEFAULT_USER_PASSWORD;
        pUser.setPassword(bCryptPasswordEncoder.encode(password));
        return studentRepository.save(pUser);
    }

    @Override
    public Student setChecked(String login) {
        Student user = findByLogin(login);
        user.setCheckedInvite(true);
        return studentRepository.save(user);
    }


    @Override
    public void deleteStudent(String login) {
        findByLogin(login);
        studentRepository.deleteByLogin(login);
    }

    @Override
    public Student findByLogin(String login) {
        return studentRepository.getStudentByLogin(login)
                .orElseThrow(() -> new ItemNotFoundException("student with name - " + login + "not found"));
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("student with id - " + id + "not found"));
    }

}
