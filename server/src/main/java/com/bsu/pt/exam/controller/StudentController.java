package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.service.GroupService;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private StudentService studentService;
    private GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping(value = "/all/{groupName}")
    public ResponseEntity<List<Student>> getAllByGroupName(@PathVariable String groupName) {
        return new ResponseEntity<List<Student>>(groupService.getStudentsByGroupName(groupName), HttpStatus.OK);
    }

    @PutMapping(value = "/u/{id}")
    public ResponseEntity<Student> setPriority(@PathVariable String id, @RequestBody PriorityDto priorityDto) {
        return new ResponseEntity<Student>(studentService.setPriority(id, priorityDto), HttpStatus.OK);
    }


}
