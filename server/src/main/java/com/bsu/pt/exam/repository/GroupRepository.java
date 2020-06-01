package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {
    Optional<Group> getGroupByGroupName(String name);

    Optional<Group> findByInviteCode(String code);
}
