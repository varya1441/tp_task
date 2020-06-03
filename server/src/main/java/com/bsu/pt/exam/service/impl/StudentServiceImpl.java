package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.StudentRepository;
import com.bsu.pt.exam.service.EventService;
import com.bsu.pt.exam.service.PriorityService;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PriorityService priorityService;
    private EventService eventService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PriorityService priorityService, EventService eventService) {
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.priorityService = priorityService;
        this.eventService = eventService;
    }

    @Override
    public Student setPriority(String login, PriorityDto priorityDto) {
        Student student = findByLogin(login);

        Priority priority = createPriority(priorityDto, student);

        student.getPriorities().add(priority);
        update(student);
        return student;
    }

    private Priority createPriority(PriorityDto priorityDto, Student student) {
        Priority priority = priority = new Priority();


        priority.setEvent(eventService.getEventByName(priorityDto.getEventName()));
        priority.setPriorities(priorityDto.getPriority());
        priority.setStudent(student);
        priorityService.addPriority(priority);
        return priority;
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
    public Student update(Student pUser) {
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

    @Override
    public Group getGroupByLogin(String name) {
        Student student=findByLogin(name);
        return findByLogin(name).getGroup();
    }

    @Override
    public Priority getStudentPriority(String login, String eventName) {
        Student student = findByLogin(login);
        Event event = eventService.getEventByName(eventName);
        return event.getPriorities().stream().findFirst().filter(p -> p.getStudent().getLogin().equals(login))
                .orElseThrow(() -> new ItemNotFoundException("priority not found for student - " + login + " event" + eventName));


    }
}
