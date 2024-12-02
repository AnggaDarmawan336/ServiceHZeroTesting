package com.hand.domain.repository;

import com.hand.api.controller.DTO.TaskDTO;
import com.hand.api.controller.DTO.UserDTO;
import com.hand.domain.entity.Task;
import com.hand.domain.entity.User;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

import java.util.List;
import java.util.Map;

public interface TaskRepository extends BaseRepository<Task> {
    Page<Task> pageTask(Task task, PageRequest pageRequest);
    List<Task> selectByEmployeeId(Long employeeId);
    Task selectDetailByTaskNumber(String taskNumber);

    List<TaskDTO> selectList(TaskDTO taskDTO);
}
