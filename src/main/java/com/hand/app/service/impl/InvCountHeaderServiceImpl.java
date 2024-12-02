package com.hand.app.service.impl;

import com.hand.api.controller.DTO.InvCountHeaderDTO;
import com.hand.api.controller.DTO.WorkFlowEventRequestDTO;
import com.hand.infra.constant.WorkFlowConstant;
import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.boot.workflow.WorkflowClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hand.app.service.InvCountHeaderService;
import org.springframework.stereotype.Service;
import com.hand.domain.entity.InvCountHeader;
import com.hand.domain.repository.InvCountHeaderRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author Angga
 * @since 2024-11-28 10:12:18
 */
@Service
public class InvCountHeaderServiceImpl implements InvCountHeaderService {
    @Autowired
    InvCountHeaderRepository invCountHeaderRepository;

    @Autowired
    WorkflowClient workflowClient;

    @Override
    public Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeader) {
        return PageHelper.doPageAndSort(pageRequest, () -> invCountHeaderRepository.selectList(invCountHeader));
    }

    @Override
    public void saveData(List<InvCountHeader> invCountHeaders) {
        List<InvCountHeader> insertList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() == null).collect(Collectors.toList());
        List<InvCountHeader> updateList = invCountHeaders.stream().filter(line -> line.getCountHeaderId() != null).collect(Collectors.toList());
        invCountHeaderRepository.batchInsertSelective(insertList);
        invCountHeaderRepository.batchUpdateByPrimaryKeySelective(updateList);
    }

    @Override
    public void approvalCallBack(Long organizationId, WorkFlowEventRequestDTO workFlowEventRequestDTO){

//        InvCountHeader invCountHeader = invCountHeaderRepository.selectCountNumber(
//                workFlowEventRequestDTO.getBusinessKey());
//
//        if (invCountHeader == null) {
//            throw new CommonException(String.format("The document[%s] doesn't exists",
//                    workFlowEventRequestDTO.getBusinessKey()));
//        }
//
//        InvCountHeaderDTO invCountHeaderDTO = new InvCountHeaderDTO();
//        BeanUtils.copyProperties(invCountHeader, invCountHeaderDTO);
//
//        invCountHeader.setWorkflowId(workFlowEventRequestDTO.getWorkFlowId());
//        invCountHeader.setCountStatus(workFlowEventRequestDTO.getDocStatus());
//
//        if (WorkFlowConstant.DocStatus.COMPLETED.equals(workFlowEventRequestDTO.getDocStatus())) {
//            invCountHeader.setApprovedTime(workFlowEventRequestDTO.getApprovedTime());
//        }
//
//        invCountHeaderRepository.updateOptional(invCountHeader,
//                InvCountHeader.FIELD_WORKFLOW_ID,
//                InvCountHeader.FIELD_COUNT_STATUS,
//                InvCountHeader.FIELD_APPROVED_TIME);
//
//        BeanUtils.copyProperties(invCountHeader, invCountHeaderDTO);
//        return invCountHeaderDTO;

        InvCountHeader invCountHeader = new InvCountHeader();
        invCountHeader.setTenantId(organizationId);
        invCountHeader.setCountNumber(workFlowEventRequestDTO.getBusinessKey());
        invCountHeader.setCountStatus(workFlowEventRequestDTO.getDocStatus());
        invCountHeader.setWorkflowId(workFlowEventRequestDTO.getWorkFlowId());
        invCountHeader.setApprovedTime(workFlowEventRequestDTO.getApprovedTime());

        // Retrieve the matching record (first match)
        InvCountHeader existingRecord = invCountHeaderRepository.selectOne(new InvCountHeader() {{
            setCountNumber(workFlowEventRequestDTO.getBusinessKey());
            setTenantId(organizationId);
        }});

        if (existingRecord != null) {
            // Update existing record using its primary key
            System.out.println("Update existing record using its primary key");
            invCountHeader.setCountHeaderId(existingRecord.getCountHeaderId()); // Set the primary key for the update
            invCountHeaderRepository.updateByPrimaryKey(invCountHeader);
        } else {
            // Insert new record
            System.out.println("Inserting new record");
            invCountHeaderRepository.insert(invCountHeader);
        }
    }
}

