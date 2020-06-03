package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Student;

import java.util.List;

public interface GroupService {
    List<Student> getStudentsByGroupName(String id);

    Group findGroupByInviteCoe(String code);

    Group getGroupByGroupName(String name);

    Group getGroup(String groupName);

    Group addGroup(Group group);

}
