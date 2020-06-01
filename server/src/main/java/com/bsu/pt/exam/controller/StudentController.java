package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Group;
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
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping(value = "/{login}")
    public ResponseEntity<Student> update(@PathVariable String login, @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.update(login, student), HttpStatus.OK);
    }


    @PutMapping(value = "/priority/{login}")
    public ResponseEntity<Student> setPriority(@PathVariable String login, @RequestBody PriorityDto priorityDto) {
        return new ResponseEntity<Student>(studentService.setPriority(login, priorityDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{login}")
    public ResponseEntity<Student> getStudent(@PathVariable String login) {
        return new ResponseEntity<Student>(studentService.findByLogin(login), HttpStatus.OK);
    }

    @GetMapping(value = "/group/{login}")
    public ResponseEntity<Group> getGroupByLogin(@PathVariable String login) {
        return new ResponseEntity<Group>(groupService.getGroupByLogin(login), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
