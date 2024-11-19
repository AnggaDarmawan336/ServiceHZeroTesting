package com.hand.app.service.impl;

import com.hand.app.service.TaskService;
import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import io.choerodon.core.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task create(Task task) {
        task.generateTaskNumber();
        taskRepository.insert(task);
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task update(Task task) {
        Task exist = taskRepository.selectByPrimaryKey(task);
        if (exist == null) {
            throw new CommonException("htdo.warn.task.notFound");
        }
        taskRepository.updateOptional(task,
                Task.FIELD_STATE,
                Task.FIELD_TASK_DESCRIPTION
        );
        return task;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskNumber(String taskNumber) {
        Task task = new Task();
        task.setTaskNumber(taskNumber);
        taskRepository.delete(task);
    }
}
