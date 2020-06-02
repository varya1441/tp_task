package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;

import java.util.List;

public interface ResultService {
    Result updateResult(String id, Result result);

    List<Student> getStudentList(String id) throws ItemNotFoundException;

    Result getResultByEventId(String eventId);

    Result getById(String id);

    Result save(Result result);

    Result createResult(String groupName);

}
