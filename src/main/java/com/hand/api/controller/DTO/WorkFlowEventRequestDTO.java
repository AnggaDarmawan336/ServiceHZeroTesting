package com.hand.api.controller.DTO;

import java.util.Date;

public class WorkFlowEventRequestDTO {
    private String businessKey;
    private Long workFlowId;
    private String docStatus;
    private Date approvedTime;


    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Long getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Long workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }
}
