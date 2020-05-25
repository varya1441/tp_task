package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.PriorityDto;
import com.bsu.pt.exam.dto.ResultDto;
import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.service.PriorityService;
import com.bsu.pt.exam.service.ResultService;
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

    @Autowired
    public ResultController(ResultService resultService, PriorityService priorityService) {
        this.resultService = resultService;
        this.priorityService = priorityService;
    }


    @PostMapping(value = "/all")
    public ResponseEntity<Result> getResult(@RequestBody ResultDto data) {

        return new ResponseEntity<Result>(resultService.save(converter(data)), HttpStatus.OK);
    }

    private Result converter(ResultDto resultDto) {
        Result result = resultService.getById(resultDto.getId());
        List<Priority> priorities = new ArrayList<>();
        for (PriorityDto rPrior : resultDto.getPriorityDtos()
        ) {
            Priority priority = priorityService.getPriorityById(rPrior.getId());

            priorities.add(priority);
        }
        result.setPriorities(priorities);
        return result;
    }
//    @GetMapping(value="/{id}")
//    public ResponseEntity<Result> getByUserId(@PathVariable String id) {
//        return new ResponseEntity<Result>(resultService.getById(id), HttpStatus.OK);
//    }

}
