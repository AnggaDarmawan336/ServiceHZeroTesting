package com.hand.infra.mapper;

import com.hand.domain.entity.Task;
import io.choerodon.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    List<Task> selectTask(Task params);
}
