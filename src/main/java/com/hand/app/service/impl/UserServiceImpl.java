package com.hand.app.service.impl;

import com.hand.app.service.UserService;
import com.hand.domain.entity.Task;
import com.hand.domain.entity.User;
import com.hand.domain.repository.TaskRepository;
import com.hand.domain.repository.UserRepository;
import com.hand.infra.repository.impl.TaskRepositoryImpl;
import io.choerodon.core.exception.CommonException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        userRepository.insert(user);
        return user;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        User exist = userRepository.selectByPrimaryKey(userId);
        if (exist == null) {
            throw new CommonException("htdo.warn.user.notFound");
        }
        userRepository.deleteByPrimaryKey(userId);

        List<Task> tasks = taskRepository.selectByEmployeeId(userId);
        if (CollectionUtils.isNotEmpty(tasks)) {
            taskRepository.batchDelete(tasks);
        }
    }
}
