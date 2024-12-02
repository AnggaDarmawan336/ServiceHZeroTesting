package com.hand.api.controller.DTO;

import com.hand.domain.entity.Task;
import lombok.Getter;
import lombok.Setter;
import org.hzero.export.annotation.ExcelSheet;
import org.springframework.stereotype.Component;

import java.util.List;

@ExcelSheet(zh = "Task Info", en = "Task Info")
//@Acce(chain = true)
@Component
@Getter
@Setter
public class TaskDTO extends Task {
    private List<Long> empIdList;

    public List<Long> getEmpIdList() {
        return empIdList;
    }

    public TaskDTO setEmpIdList(List<Long> empIdList) {
        this.empIdList = empIdList;
        return this;
    }
}
