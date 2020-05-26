package com.bsu.pt.exam.dto;

public class StudentDto {
    private String id;
    private PriorityDto priorityDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PriorityDto getPriorityDto() {
        return priorityDto;
    }

    public void setPriorityDto(PriorityDto priorityDto) {
        this.priorityDto = priorityDto;
    }
}
