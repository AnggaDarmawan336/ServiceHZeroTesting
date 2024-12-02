package com.hand.infra.mapper;

import com.hand.api.controller.DTO.UserDTO;
import com.hand.domain.entity.Task;
import com.hand.domain.entity.User;
import io.choerodon.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUser(User params);

    List<UserDTO> selectList(UserDTO userDTO);
    User selectByUserAccount(@Param(value = "userAccount") Long userAccount);
}
