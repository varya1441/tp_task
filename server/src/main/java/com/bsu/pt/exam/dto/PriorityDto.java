package com.bsu.pt.exam.dto;

import java.util.Map;
import java.util.TreeMap;

public class PriorityDto {

    private Map<Integer, Integer> priority = new TreeMap<>();


    public Map<Integer, Integer> getPriority() {
        return priority;
    }

    public void setPriority(Map<Integer, Integer> priority) {
        this.priority = priority;
    }
}
