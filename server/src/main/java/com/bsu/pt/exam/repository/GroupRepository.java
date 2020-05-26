package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
    Group getGroupByGroupName(String name);
}
