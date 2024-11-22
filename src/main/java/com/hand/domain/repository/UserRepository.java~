package com.hand.domain.repository;

import com.hand.domain.entity.Task;
import com.hand.domain.entity.User;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {
    Page<User> pageUser(User user, PageRequest pageRequest);
    List<User> selectByUserId(Long userId);
    User selectDetailByUserNumber(String userNumber);
}
