package com.bsu.pt.exam.service;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Student;

public interface StudentService {
    Student findByLogin(String login);

    Student save(Student user);

    Student setChecked(String login);

    Student update(Student pUser);

    Student setPriority(String studentId, PriorityDto priorityDto);

    void deleteStudent(String id);

    Group getGroupByLogin(String name);

    Priority getStudentPriority(String login, String eventName);

}
