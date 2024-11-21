package com.hand.app.service.impl;

import com.hand.api.controller.DTO.MessageRequest;
import com.hand.domain.entity.Task;
import com.hand.domain.repository.TaskRepository;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.FlyBookMsgType;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class MessageService {

    private TaskRepository taskRepository;

    private static Logger logger = Logger.getLogger(MessageService.class.getName());

    private final String TEMPLATE_CODE_FEISHU = "TEST-FEISHU-47839";


    private MessageClient messageClient;
    private String TEMPlATE_CODE = "DEMO-47839";
    private String LANGUAGE_CODE = "en_US";
    private String SERVER_CODE = "FEIYU";
    private String SUBJECT = "FROM CODE";

    public MessageService(MessageClient messageClient){
        this.messageClient = messageClient;
    }

    public void sendEmail(String context, String email, Long tenantId)
    {
        Receiver receiver = new Receiver();
        receiver.setEmail(email);
        receiver.setTargetUserTenantId(tenantId);

        messageClient.sendCustomEmail(
                tenantId,
                SERVER_CODE,
                SUBJECT,
                context,
                Collections.singletonList(receiver),
                null,
                null,
                null
        );
    }

    public void sendNotification(Long tenantId, Long userId, List<MessageRequest> message)
    {
        Receiver receiver = new Receiver();
        receiver.setUserId(userId);
        receiver.setTargetUserTenantId(tenantId);

        for (MessageRequest request : message) {
            Map<String, String> args = new HashMap<>();
            args.put("null", request.getUserName());

            Message message1 = messageClient.sendWebMessage(tenantId, TEMPlATE_CODE, LANGUAGE_CODE,
                    Collections.singletonList(receiver),
                    args);
        }
    }

    public Message sendFlyBook(long tenantId, String contexMsg, String email) {
        List<Map<String, String>> receivers = new ArrayList<>();
        Map<String, String> receiver = new HashMap<>();
        receiver.put("email", email);
        receivers.add(receiver);

        Map<String, Object> args = new HashMap<>();
        args.put("name", "Angga Darmawan");
        args.put("employeeId", 47839);
        args.put("email", "angga.darmawan@hand-global.com");
//
//        // Panggil metode selectByEmployeeId untuk mendapatkan data user
//        List<Task> userData = taskRepository.selectByEmployeeId(47839L);
//
//        // Ambil data name, employeeId, dan email dari hasil query
//        String name = (String) userData.get(0).getEmployeeName();
//        Long employeeId = (Long) userData.get(0).getEmployeeId();
//        String userEmail = (String) userData.get(0).getEmail();
//
//        // Gunakan data user untuk mengirimkan pesan
//        List<Map<String, String>> receivers = new ArrayList<>();
//        Map<String, String> receiver = new HashMap<>();
//        receiver.put("email", userEmail);
//        receivers.add(receiver);
//
//        Map<String, Object> args = new HashMap<>();
//        args.put("name", name);
//        args.put("employeeId", employeeId);
//        args.put("email", userEmail);


        return messageClient.sendFlyBook(
                tenantId,
                SERVER_CODE,
                TEMPLATE_CODE_FEISHU,
                FlyBookMsgType.TEXT,
                LANGUAGE_CODE,
                receivers,
                args
        );
    }
}

