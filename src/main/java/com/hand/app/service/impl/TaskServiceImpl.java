package com.hand.app.service.impl;

import com.hand.api.controller.DTO.TaskDTO;
import com.hand.app.service.TaskService;
import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.boot.platform.lov.adapter.LovAdapter;
import org.hzero.boot.platform.lov.dto.LovValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String CODE_RULE = "DEMO-47839";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    CodeRuleBuilder codeRuleBuilder;

    @Autowired
    LovAdapter lovAdapter;

    @Override
    public Task create(Task task) {

          Map<String, String> args = new HashMap<>();
          String name = DetailsHelper.getUserDetails().getRealName();
          args.put("customSegment", name + "-");

          String taskNumber = codeRuleBuilder.generateCode(CODE_RULE, args);
          task.setTaskNumber(taskNumber);

          boolean indicator = false;
          if (task.getTaskType() != null) {
              List<LovValueDTO> taskTypes = lovAdapter.queryLovValue("DEMO-47839-TASKTYPE", task.getTenantId());
//              taskTypes.contains(Task.getTaskType());
              for (int i = 0; i < taskTypes.size(); i++) {
                  if (taskTypes.get(i).getValue().equals(task.getTaskType())) {
                      indicator = true;
                      break;
                  }
              }
          }else {
              throw new CommonException("demo-47839.invalid_tasktype");
          }

          taskRepository.insert(task);
          return task;
//        task.generateTaskNumber();
//        taskRepository.insert(task);
//        return task;
    }

    @Override
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
    public void deleteByTaskNumber(String taskNumber) {
        Task task = new Task();
        task.setTaskNumber(taskNumber);
        taskRepository.delete(task);
    }

    @Override
    public List<TaskDTO> selectList(TaskDTO taskDTO) {
        return taskRepository.selectList(taskDTO);
    }
}
