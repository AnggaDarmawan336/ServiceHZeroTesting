package com.hand.infra.util;

import com.hand.app.service.impl.MessageService;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@JobHandler("DEMO-47839")
public class DemoJob implements IJobHandler {

    private static final Logger log = LoggerFactory.getLogger(DemoJob.class);
    private final MessageService messageService;

    public DemoJob(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        log.info("This is log service from: 47839 id: {}", map.get("userId"));

        String email = "shaoqin.zhou@hand-china.com";
        Message message1 = messageService.sendFlyBook(0, "hello" , email);
        return ReturnT.SUCCESS;
    }
}
