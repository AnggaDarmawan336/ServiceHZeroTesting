package com.hand.app.service;

import com.hand.api.controller.DTO.WorkFlowClientDTO;
import org.hzero.boot.workflow.dto.RunInstance;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface WorkFlowCLientService {

    String startWorkFlowClient(Long organizationId, WorkFlowClientDTO workflowClientDTO);
    void withDrawlSpecific(Long organizationId, WorkFlowClientDTO workflowClientDTO );
    List<RunTaskHistory> getApprovedHistory(Long organizationId, String flowKey, String businessKey);
}
