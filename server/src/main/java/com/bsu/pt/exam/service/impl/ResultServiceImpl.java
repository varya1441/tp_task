package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.algo.AlgorithmExecution;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.ResultRepository;
import com.bsu.pt.exam.service.EventService;
import com.bsu.pt.exam.service.GroupService;
import com.bsu.pt.exam.service.ResultService;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    ResultRepository resultRepository;
    GroupService groupService;
    StudentService studentService;
    EventService eventService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, GroupService groupService, StudentService studentService, EventService eventService) {
        this.resultRepository = resultRepository;
        this.groupService = groupService;
        this.studentService = studentService;
        this.eventService = eventService;
    }


    @Override
    public Result getResultByEventId(String eventId) {
        return resultRepository.getResultByEventId(eventId);
    }

    //TODO
    @Override
    public Result updateResult(Result pResult) {

        return resultRepository.save(pResult);
    }

    @Override
    public List<Student> getStudentList(String id) {
        Result result = getById(id);
        return result.getStudents();

    }

    public Result createResult(String eventName, String groupName) {
        Event event = eventService.getEventByName(eventName);
        Result result = new Result();
        result.setEvent(event);
        save(result);

        List<Student> students = groupService.getStudentsByGroupName(groupName);
        List<String> resultStudentLogins = new AlgorithmExecution().getSolution(students);
        students.sort(Comparator.comparing(item -> resultStudentLogins.indexOf(item.getLogin())));
        for (Student student :
                students) {
            student.setResult(result);
            studentService.update(student);
        }
        event.setResult(result);
        eventService.updateEventResult(event);
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
