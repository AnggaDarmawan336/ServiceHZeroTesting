package com.hand.app.service.impl;

import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.domain.repository.UserRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.app.service.ImportHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ImportService(templateCode = "DEMO-CLIENT-47839")
public class ImportServiceImpl extends ImportHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public ImportServiceImpl(ObjectMapper objectMapper, TaskRepository taskRepository, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean doImport(String data) {
        try {
            Task task = objectMapper.readValue(data, Task.class);

            Long employeeId = task.getEmployeeId();
            String taskNumber = task.getTaskNumber();

            if (!taskNumber.matches("^[A-Za-z0-9 ,.!?\\'\\\"-]*$")) {
                throw new RuntimeException("Task number contains invalid characters");
            }
            if (!userRepository.existsWithPrimaryKey(employeeId)) {
                throw new RuntimeException("Employee ID does not exist in the database");
            }
            taskRepository.insertSelective(task);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
