package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, String> {
}
