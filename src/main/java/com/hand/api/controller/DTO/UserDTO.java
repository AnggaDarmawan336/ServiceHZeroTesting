package com.hand.api.controller.DTO;

import com.hand.domain.entity.User;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

import java.util.List;

@ExcelSheet(zh = "User info", en = "User info")
public class UserDTO extends User {
    @ExcelColumn(promptCode = "children", promptKey = "children", child = true)
    private List<TaskDTO> taskList;

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

}
