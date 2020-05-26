package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.ResultDto;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.service.PriorityService;
import com.bsu.pt.exam.service.ResultService;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/result")
@CrossOrigin(origins = "http://localhost:3000")
public class ResultController {
    private ResultService resultService;
    private PriorityService priorityService;
    private StudentService studentService;

    @Autowired
    public ResultController(ResultService resultService, PriorityService priorityService, StudentService studentService) {
        this.resultService = resultService;
        this.priorityService = priorityService;
        this.studentService = studentService;
    }


    @PostMapping(value = "/all")
    public ResponseEntity<Result> getResult(@RequestBody ResultDto data) {

        return new ResponseEntity<Result>(resultService.save(converter(data)), HttpStatus.OK);
    }

    private Result converter(ResultDto resultDto) {
        Result result = new Result();
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        studentService.save(student);
        students.add(student);
        result.setStudents(students);
//        for (StudentDto rPrior : resultDto.getStudentDtos()
//        ) {
//            Priority priority = priorityService.getPriorityById(rPrior.getPriorityDto().getId());
//
//            priorities.add(priority);
//        }
//        result.setPriorities(priorities);
        return result;
    }
//    @GetMapping(value="/{id}")
//    public ResponseEntity<Result> getByUserId(@PathVariable String id) {
//        return new ResponseEntity<Result>(resultService.getById(id), HttpStatus.OK);
//    }

}
