package com.bsu.pt.exam.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {
    private String id;
    private List<StudentDto> studentDtos=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<StudentDto> getStudentDtos() {
        return studentDtos;
    }

    public void setStudentDtos(List<StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }
}
