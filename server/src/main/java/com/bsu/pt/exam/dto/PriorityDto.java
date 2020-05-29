package com.bsu.pt.exam.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PriorityDto {
    private String id;
    private Map<Integer,Integer> priority=new TreeMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer, Integer> getPriority() {
        return priority;
    }

    public void setPriority(Map<Integer, Integer> priority) {
        this.priority = priority;
    }
}
