package com.hand.app.service;

import com.hand.api.controller.DTO.InvCountHeaderDTO;
import com.hand.api.controller.DTO.WorkFlowClientDTO;
import com.hand.api.controller.DTO.WorkFlowEventRequestDTO;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import com.hand.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author Angga
 * @since 2024-11-28 10:12:17
 */
public interface InvCountHeaderService {

    /**
     * 查询数据
     *
     * @param pageRequest     分页参数
     * @param invCountHeaders 查询条件
     * @return 返回值
     */
    Page<InvCountHeader> selectList(PageRequest pageRequest, InvCountHeader invCountHeaders);

    /**
     * 保存数据
     *
     * @param invCountHeaders 数据
     */
    void saveData(List<InvCountHeader> invCountHeaders);

    void approvalCallBack(Long organizationId, WorkFlowEventRequestDTO workFlowEventRequestDTO);

}

