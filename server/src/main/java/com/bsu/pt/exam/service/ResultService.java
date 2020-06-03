package com.bsu.pt.exam.service;

import com.bsu.pt.exam.entity.Result;

public interface ResultService {
    Result updateResult(Result uResult);

    Result getResultByEventId(String eventId);

    Result getById(String id);

    Result save(Result result);

    Result createResult(String eventId, String groupName);

}
