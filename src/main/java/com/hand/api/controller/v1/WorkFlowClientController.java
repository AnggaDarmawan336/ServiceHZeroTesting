package com.hand.api.controller.v1;

import com.hand.api.controller.DTO.WorkFlowClientDTO;
import com.hand.app.service.WorkFlowCLientService;
import com.hand.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.workflow.dto.RunTaskHistory;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = SwaggerTags.WORKFLOW)
@RestController("WorkFlowClientController.v1")
@RequestMapping("/v1/work-flow")
public class WorkFlowClientController {

    @Autowired
    WorkFlowCLientService workFlowCLientService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "Start a workflow")
    @PostMapping("/start/{organizationId}")
    public ResponseEntity<String> startWorkflow(@PathVariable("organizationId") Long organizationId, @RequestBody WorkFlowClientDTO workFlowClientDTO) {
        workFlowCLientService.startWorkFlowClient(organizationId, workFlowClientDTO);
        return Results.success();
    }

    @ApiOperation(value = "withdrawl specific")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/{organizationId}")
    public ResponseEntity<String> withdraw(@PathVariable("organizationId") Long tenantId, WorkFlowClientDTO workflowStartDTO) {
        workFlowCLientService.withDrawlSpecific(tenantId, workflowStartDTO);
        return Results.success("Workflow withdrawn");
    }

}
