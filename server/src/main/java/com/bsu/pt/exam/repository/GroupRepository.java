package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String> {
    Group getGroupByGroupName(String name);
}
