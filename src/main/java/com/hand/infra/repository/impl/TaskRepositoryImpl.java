package com.hand.infra.repository.impl;

import com.hand.api.controller.DTO.TaskDTO;
import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import com.hand.infra.mapper.TaskMapper;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.boot.message.MessageClient;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.mybatis.common.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class TaskRepositoryImpl extends BaseRepositoryImpl<Task> implements TaskRepository {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private MessageClient messageClient;

    public TaskRepositoryImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Page<Task> pageTask(Task task, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, () -> taskMapper.selectTask(task));
    }

    @Override
    public List<Task> selectByEmployeeId(Long employeeId) {
        Task task = new Task();
        task.setEmployeeId(employeeId);
        return this.selectOptional(task, new Criteria()
                .select(Task.FIELD_ID, Task.FIELD_EMPLOYEE_ID, Task.FIELD_STATE, Task.FIELD_TASK_DESCRIPTION)
                .where(Task.FIELD_EMPLOYEE_ID)
        );
    }

    @Override
    public Task selectDetailByTaskNumber(String taskNumber) {
        Task params = new Task();
        params.setTaskNumber(taskNumber);
        List<Task> tasks = taskMapper.selectTask(params);
        return CollectionUtils.isNotEmpty(tasks) ? tasks.get(0) : null;
    }

    @Override
    public List<TaskDTO> selectList(TaskDTO taskDTO) {
        return taskMapper.selectList(taskDTO);
    }


}
