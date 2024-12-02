package com.hand.domain.repository;

import com.hand.api.controller.DTO.InvCountHeaderDTO;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.domain.entity.InvCountHeader;

import java.util.List;

/**
 * Inventory Count Header Table(InvCountHeader)资源库
 *
 * @author Angga
 * @since 2024-11-28 10:12:17
 */
public interface InvCountHeaderRepository extends BaseRepository<InvCountHeader> {
    /**
     * 查询
     *
     * @param invCountHeader 查询条件
     * @return 返回值
     */
    List<InvCountHeader> selectList(InvCountHeader invCountHeader);

    /**
     * 根据主键查询（可关联表）
     *
     * @param countHeaderId 主键
     * @return 返回值
     */
    InvCountHeader selectByPrimary(Long countHeaderId);

    InvCountHeader selectCountNumber(String countNumber);

    InvCountHeader getId(String countNumber);

}
