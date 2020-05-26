package com.bsu.pt.exam.dto;

import java.util.Map;

public class GroupDto {
    Map<String, PriorityDto> priorityDtoMap;

    public Map<String, PriorityDto> getPriorityDtoMap() {
        return priorityDtoMap;
    }

    public void setPriorityDtoMap(Map<String, PriorityDto> priorityDtoMap) {
        this.priorityDtoMap = priorityDtoMap;
    }
}
