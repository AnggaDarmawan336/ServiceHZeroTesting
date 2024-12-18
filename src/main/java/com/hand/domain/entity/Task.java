package com.hand.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hzero.export.annotation.ExcelColumn;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@ApiModel("任务信息")
@ModifyAudit
@VersionAudit
@Table(name = "todo_task")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task extends AuditDomain {
    public static final String FIELD_ID = "id";
    public static final String FIELD_EMPLOYEE_ID = "employeeId";
    public static final String FIELD_STATE = "state";
    public static final String FIELD_TASK_DESCRIPTION = "taskDescription";
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "error.employeeId.null")
    @ApiModelProperty("用户ID")
    @ExcelColumn(en = "employeeId")
    private Long employeeId;
    @ApiModelProperty("任务状态")
    @ExcelColumn(en = "state")
    private String state;
    @ApiModelProperty("任务编号")
    @ExcelColumn(en = "taskNumber")
    private String taskNumber;
    @Length(max = 240)
    @ApiModelProperty("任务描述")
    @ExcelColumn(en = "taskDescription")
    private String taskDescription;
    @NotNull
    @ApiModelProperty("租户ID")
    private Long tenantId;
    @Transient
    @ApiModelProperty("员工编号")
    private String employeeNumber;
    @Transient
    @ApiModelProperty("员工姓名")
    private String employeeName;
    private String taskType;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public @NotNull(message = "error.employeeId.null") Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NotNull(message = "error.employeeId.null") Long employeeId) {
        this.employeeId = employeeId;
    }

    public @NotNull Long getTenantId() {
        return tenantId;
    }


    public void setTenantId(@NotNull Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public @Length(max = 240) String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(@Length(max = 240) String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void generateTaskNumber() {
        this.taskNumber = UUID.randomUUID().toString().replace("-", "");
    }
}
