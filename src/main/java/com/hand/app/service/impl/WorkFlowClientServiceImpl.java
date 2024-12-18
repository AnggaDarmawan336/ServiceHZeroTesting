package com.hand.app.service.impl;

import com.hand.api.controller.DTO.WorkFlowClientDTO;
import com.hand.app.service.WorkFlowCLientService;
import com.hand.domain.entity.InvCountHeader;
import com.hand.domain.repository.InvCountHeaderRepository;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkFlowClientServiceImpl implements WorkFlowCLientService {

    @Autowired
    WorkFlowClientDTO workFlowClientDTO;

    @Autowired
    WorkflowClient workFlowClient;

    @Autowired
    InvCountHeaderRepository invCountHeaderRepository;

    public WorkFlowClientServiceImpl(WorkflowClient workFlowClient) {
        this.workFlowClient = workFlowClient;
        this.workFlowClientDTO = new WorkFlowClientDTO();
    }

    @Override
    public String startWorkFlowClient(Long organizationId, WorkFlowClientDTO request) {
        InvCountHeader countHeader = invCountHeaderRepository.getId(workFlowClientDTO.getBusinessKey());

        // Check if the count header ID is null, indicating the business key was not found
        if (countHeader == null || countHeader.getCountHeaderId() == null) {
            throw new CommonException("demo-47839.workflow_not_found", workFlowClientDTO.getBusinessKey());
        }
        RunInstance workFlowStart = workFlowClient.startInstanceByFlowKey(organizationId,
                workFlowClientDTO.getFlowKey(),
                workFlowClientDTO.getBusinessKey(),
                workFlowClientDTO.getDimension(),
                workFlowClientDTO.getStarter(),
                workFlowClientDTO.getVariableMap());
//        WorkInfoDTO workInfoDTO = new WorkInfoDTO();
//        workInfoDTO.setWorkFlowId(workFlowStart.getDeploymentId());
//        workInfoDTO.setCountNumber(workFlowStart.getBusinessKey());
//        workInfoDTO.setCountStatus("DRA");
//        invCountHeaderService.saveHeader(organizationId,workInfoDTO);
        return workFlowStart.getStatus();
    }

    @Override
    public void withDrawlSpecific(Long organizationId, WorkFlowClientDTO request) {
        workFlowClient.flowWithdrawFlowKey(organizationId, request.getFlowKey(), request.getBusinessKey());
    }

    @Override
    public List<RunTaskHistory> getApprovedHistory(Long organizationId, String flowKey, String businessKey) {
        return workFlowClient.approveHistoryByFlowKey(organizationId, flowKey, businessKey);
    }
}
