package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Student;

import java.util.List;

public interface GroupService {
    List<Student> getStudentsByGroupName(String id);

    Student getGroupLeader(String id);

    Group getGroupByLogin(String name);

    Group findGroupByInviteCoe(String code);

    Group getGroupByGroupName(String name);

    boolean addStudent(String name, Student student);

    Group getGroup(String groupName);

    Group addGroup(Group group);

    Group update(String name, Group group);
}
