package com.hand.infra.mapper;

import io.choerodon.mybatis.common.BaseMapper;
import com.hand.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)应用服务
 *
 * @author Angga
 * @since 2024-11-28 10:12:17
 */
public interface InvCountHeaderMapper extends BaseMapper<InvCountHeader> {
    /**
     * 基础查询
     *
     * @param invCountHeader 查询条件
     * @return 返回值
     */
    List<InvCountHeader> selectList(InvCountHeader invCountHeader);

    InvCountHeader getId(String count_number);

    InvCountHeader selectCountNumber(String countNumber);
}

