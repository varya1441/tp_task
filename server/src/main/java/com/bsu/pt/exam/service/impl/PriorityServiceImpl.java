package com.bsu.pt.exam.service.impl;

import com.bsu.pt.exam.entity.Priority;
import com.bsu.pt.exam.exception.ItemNotFoundException;
import com.bsu.pt.exam.repository.PriorityRepository;
import com.bsu.pt.exam.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {
    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Priority getPriorityById(String id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("priority with id - " + id + "not found"));
    }

    @Override
    public Priority addPriority(Priority priority) {
        return priorityRepository.save(priority);
    }
}
