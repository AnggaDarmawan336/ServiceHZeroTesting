package com.hand.api.controller.DTO;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvCountHeaderDTO {
    @ApiModelProperty(value = "Count Mode")
    private String countMode;

    @ApiModelProperty(value = "Count Number", required = true)
    @NotBlank
    private String countNumber;

    @ApiModelProperty(value = "Count Status", required = true)
    @NotBlank
    private String countStatus;

    @ApiModelProperty(value = "Count Type")
    private String countType;

    @ApiModelProperty(value = "Counter")
    private Object countorIds;

    @ApiModelProperty(value = "Remark")
    private Object remark;

    @ApiModelProperty(value = "Supervisor")
    private Object supervisorIds;

    @ApiModelProperty(value = "Tenant Id", required = true)
    @NotNull
    private Long tenantId;

    @ApiModelProperty(value = "Workflow Id")
    private Long workflowId;

    public String getCountMode() {
        return countMode;
    }

    public void setCountMode(String countMode) {
        this.countMode = countMode;
    }

    public @NotBlank String getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(@NotBlank String countNumber) {
        this.countNumber = countNumber;
    }

    public @NotBlank String getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(@NotBlank String countStatus) {
        this.countStatus = countStatus;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public Object getCountorIds() {
        return countorIds;
    }

    public void setCountorIds(Object countorIds) {
        this.countorIds = countorIds;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getSupervisorIds() {
        return supervisorIds;
    }

    public void setSupervisorIds(Object supervisorIds) {
        this.supervisorIds = supervisorIds;
    }

    public @NotNull Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(@NotNull Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }
}
