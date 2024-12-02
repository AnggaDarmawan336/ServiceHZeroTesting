package com.hand.app.service;

import com.hand.api.controller.DTO.TaskDTO;
import com.hand.domain.entity.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task update(Task task);
    void deleteByTaskNumber(String taskNumber);
    List<TaskDTO> selectList (TaskDTO taskDTO);
}
