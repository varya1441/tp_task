package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.GroupRepository;
import com.bsu.pt.exam.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group getGroupByGroupName(String name) {
        return  groupRepository.findById(name)
                .orElseThrow(() -> new ItemNotFoundException("group with id - " + name + "not found"));
    }

    @Override
    public Student getGroupLeaderByGroupName(String id) {
        Group group = this.getGroupByGroupName(id);
        return group.getGroupLeader();
    }

    @Override
    public List<Student> getStudentsByGroupName(String name) {
        Group group = this.getGroupByGroupName(name);
        return group.getStudents();
    }

    @Override
    public boolean addStudent(String name,Student student) {
        Group group = this.getGroupByGroupName(name);
        group.getStudents().add(student);
        return true;
    }
}
