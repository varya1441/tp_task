package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.ResultRepository;
import com.bsu.pt.exam.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultServiceImpl implements ResultService {
    ResultRepository resultRepository;
    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Result updateResult(long id,Result result) {
        return null;
    }

    @Override
    public List<Student> getStudentList(long id) {
        Result result=resultRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("result with id - " + id + "not found"));
        return result.getStudents();

    }

    @Override
    public Result getById(long id) {
        return null;
    }
}
