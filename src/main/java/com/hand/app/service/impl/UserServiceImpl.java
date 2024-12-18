package com.hand.app.service.impl;

import com.hand.api.controller.DTO.TaskDTO;
import com.hand.api.controller.DTO.UserDTO;
import com.hand.app.service.TaskService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TaskService taskService;

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

    @Override
    public List<UserDTO> exportData(UserDTO userDTO) {
        List<UserDTO> userList = userRepository.selectList(userDTO);
        List<Long> userIdlist = new ArrayList<>();
        userList.forEach(user -> userIdlist.add(user.getId()));
        Map<Long, List<TaskDTO>> taskMap = taskService.selectList(new TaskDTO().setEmpIdList(userIdlist))
                .stream()
                .collect(Collectors.groupingBy(TaskDTO::getEmployeeId));
        userList.forEach(user -> user.setTaskList(taskMap.get(user.getId())));
        return userList;
    }
}