package com.hand.api.controller.v1;

import com.hand.app.service.TaskService;
import com.hand.config.SwaggerTags;
import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.*;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

@Api(tags = SwaggerTags.TASK)
@RestController("taskController.v1")
@RequestMapping("/v1/{organizationId}/tasks")
public class TaskController extends BaseController {
    private TaskService taskService;
    private TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据taskNumber分页查询task")
    @GetMapping
    public ResponseEntity<Page<Task>> list(@PathVariable("organizationId") Long tenantId,
                                           Task task,
                                           PageRequest pageRequest
    ){
        task.setTenantId(tenantId);
        return Results.success(taskRepository.pageTask(task, pageRequest));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "创建task")
    @PostMapping
    @SuppressWarnings("unused")
    public ResponseEntity<Task> create(@PathVariable("organizationId") Long tenantId,
                                        @RequestBody Task task
    ){
        task.setTenantId(tenantId);
        this.validObject(task);
        return Results.success(taskService.create(task));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "更新task")
    @PutMapping
    public ResponseEntity<Task> update(@PathVariable("organizationId") Long tenantId,
                                       @RequestBody Task task
    ){
        this.validObject(task);
        SecurityTokenHelper.validToken(task);
        return Results.success(taskService.update(task));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据taskNumber查询task")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "taskNumber", value = "任务编号", paramType = "string", dataType = "string", required = true)
    })
    @GetMapping("/{taskNumber}")
    public ResponseEntity<Task> query(@PathVariable Long organizationId,
                                      @PathVariable String taskNumber
    ){
        return Results.success(taskRepository.selectDetailByTaskNumber(taskNumber));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据taskNumber删除task")
    @DeleteMapping("/{taskNumber}")
    public void delete(@PathVariable Long organizationId,
                       @PathVariable @ApiParam(value = "任务编号") String taskNumber
    ){
        taskService.deleteByTaskNumber(taskNumber);
    }
}
