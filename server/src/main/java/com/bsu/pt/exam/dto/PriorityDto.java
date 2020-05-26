package com.bsu.pt.exam.dto;

import java.util.List;

public class PriorityDto {
    private String id;
    private List<Integer> priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getPriority() {
        return priority;
    }

    public void setPriority(List<Integer> priority) {
        this.priority = priority;
    }
}
