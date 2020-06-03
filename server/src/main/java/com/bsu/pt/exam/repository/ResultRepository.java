package com.bsu.pt.exam.repository;

import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, String> {
    Optional<Result> getResultByEvent(Event event);
}
