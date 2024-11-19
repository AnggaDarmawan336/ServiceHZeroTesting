package com.hand.app.service;

import com.hand.domain.entity.Task;

public interface TaskService {
    Task create(Task task);
    Task update(Task task);
    void deleteByTaskNumber(String taskNumber);
}
