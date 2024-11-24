package com.hand.domain.repository;

import com.hand.domain.entity.Task;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

import java.util.List;
import java.util.Map;

public interface TaskRepository extends BaseRepository<Task> {
    Page<Task> pageTask(Task task, PageRequest pageRequest);
    List<Task> selectByEmployeeId(Long employeeId);
    Task selectDetailByTaskNumber(String taskNumber);
}
