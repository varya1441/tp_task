package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.algo.AlgorithmExecution;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.ResultRepository;
import com.bsu.pt.exam.service.GroupService;
import com.bsu.pt.exam.service.ResultService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    ResultRepository resultRepository;
    GroupService groupService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, GroupService groupService) {
        this.resultRepository = resultRepository;
        this.groupService = groupService;
    }


    @Override
    public Result getResultByEventId(String eventId) {
        return resultRepository.getResultByEventId(eventId);
    }

    @Override
    public Result updateResult(String id, Result pResult) {
        Result result = getById(id);
        BeanUtils.copyProperties(pResult, result, "id");
        return resultRepository.save(result);
    }

    @Override
    public List<Student> getStudentList(String id) {
        Result result = getById(id);
        return result.getStudents();

    }

    public Result createResult(String groupName) {
        Result result = new Result();
        List<Student> students = groupService.getStudentsByGroupName(groupName);
        new AlgorithmExecution().getSolution(students);
        result.setStudents(students);
        return result;
    }

    @Override
    public Result getById(String id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("result with id - " + id + "not found"));
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }
}
