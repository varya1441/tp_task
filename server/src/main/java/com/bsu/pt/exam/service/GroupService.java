package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Student;

import java.util.List;

public interface GroupService {
    List<Student> getStudentsByGroupName(String id);
    Student getGroupLeaderByGroupName(String id);
    Group getGroupByGroupName(String name);
    boolean addStudent(String name,Student student);
}
