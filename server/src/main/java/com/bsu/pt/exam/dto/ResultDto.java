package com.bsu.pt.exam.dto;

import com.bsu.pt.exam.entity.Priority;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {
    private String id;
    private List<PriorityDto> priorityDtos=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PriorityDto> getPriorityDtos() {
        return priorityDtos;
    }

    public void setPriorityDtos(List<PriorityDto> priorityDtos) {
        this.priorityDtos = priorityDtos;
    }
}
