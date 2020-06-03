package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.Priority;
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
    public ResponseEntity<Student> setChecked(@PathVariable String login) {
        return new ResponseEntity<Student>(studentService.setChecked(login), HttpStatus.OK);
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
        return new ResponseEntity<Group>(studentService.getGroupByLogin(login), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{login}")
    public ResponseEntity<?> delete(@PathVariable String login) {
        studentService.deleteStudent(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{eventName}/priority/{login}")
    public ResponseEntity<Priority> getStudentPriority(@PathVariable String eventName, @PathVariable String login) {
        return new ResponseEntity<Priority>(studentService.getStudentPriority(login, eventName), HttpStatus.OK);
    }

    @PutMapping(value = "/priority/u/{login}")
    public ResponseEntity<Priority> updatePriority(@PathVariable String login, @RequestBody PriorityDto priorityDto) {
        return new ResponseEntity<Priority>(studentService.updatePriority(login, priorityDto), HttpStatus.OK);
    }


}
