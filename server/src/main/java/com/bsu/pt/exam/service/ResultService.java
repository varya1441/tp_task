package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;

import java.util.List;

public interface ResultService {
    Result updateResult(long id,Result result);
    List<Student> getStudentList(long id) throws ItemNotFoundException;
    Result getById(long id);

}
