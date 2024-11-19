package com.hand.infra.repository.impl;

import com.hand.domain.entity.Task;
import com.hand.domain.entity.User;
import com.hand.domain.repository.UserRepository;
import com.hand.infra.mapper.UserMapper;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.hzero.mybatis.base.BaseRepository;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.mybatis.common.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Page<User> pageUser(User user, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, () -> userMapper.selectUser(user));
    }

    @Override
    public List<User> selectByUserId(Long userId) {
        User user = new User();
        user.setId(userId);
        return this.selectOptional(user, new Criteria());
    }

    @Override
    public User selectDetailByUserNumber(String userNumber) {
        User params = new User();
        params.setEmployeeNumber(userNumber);
        List<User> users = userMapper.selectUser(params);
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
    }
}
