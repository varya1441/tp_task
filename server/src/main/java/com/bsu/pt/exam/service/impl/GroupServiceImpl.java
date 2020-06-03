package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Role;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.exception.NoSuchInviteCodeOrUser;
import com.bsu.pt.exam.repository.GroupRepository;
import com.bsu.pt.exam.service.GroupService;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.BeanUtils;
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
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(String groupName) {
        Group group;
        try {
            group = getGroupByGroupName(groupName);

        } catch (ItemNotFoundException e) {
            group = new Group(groupName);
            addGroup(group);
        }
        return group;

    }

    @Override
    public Group findGroupByInviteCoe(String code) {
        return groupRepository.findByInviteCode(code)
                .orElseThrow(() -> new NoSuchInviteCodeOrUser("invite code note found"));
    }

    @Override
    public Group getGroupByGroupName(String name) {
        return groupRepository.findById(name)
                .orElseThrow(() -> new ItemNotFoundException("group with id - " + name + "not found"));
    }



    @Override
    public Student getGroupLeader(String id) {
        Group group = this.getGroupByGroupName(id);
        return group.getStudents().stream().filter(s -> s.getRole().equals(Role.LEADER)).findFirst().orElseThrow(() -> new ItemNotFoundException("no groupLeader in group - " + id));
    }

    @Override
    public List<Student> getStudentsByGroupName(String name) {
        Group group = this.getGroupByGroupName(name);
        return group.getStudents();
    }

    @Override
    public boolean addStudent(String name, Student student) {
        Group group = this.getGroupByGroupName(name);
        group.getStudents().add(student);
        return true;
    }

    @Override
    public Group update(String name, Group group) {
        Group uGroup = getGroupByGroupName(name);
        BeanUtils.copyProperties(group, uGroup, "groupName");
        return groupRepository.save(uGroup);
    }
}
