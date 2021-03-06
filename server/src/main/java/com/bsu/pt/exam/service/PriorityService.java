package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Priority;

public interface PriorityService {
    Priority getPriorityById(String id);

    Priority addPriority(Priority priority);
    Priority update(Priority priority);
}
