package com.bsu.pt.exam.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {

    private List<StudentDto> studentDtos = new ArrayList<>();


    public List<StudentDto> getStudentDtos() {
        return studentDtos;
    }

    public void setStudentDtos(List<StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }
}
