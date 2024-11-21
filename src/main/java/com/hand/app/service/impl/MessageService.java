package com.hand.app.service.impl;

import com.hand.api.controller.DTO.MessageRequest;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private MessageClient messageClient;
    private String TEMPlATE_CODE = "DEMO-47839";
    private String LANGUAGE_CODE = "en_US";
    private String SERVER_CODE = "47839";
    private String SUBJECT = "FROM CODE";

    public MessageService(MessageClient messageClient){
        this.messageClient = messageClient;
    }

//    public void sendStationMessage(Long tenantId, Long userId, List<MessageRequest> message){
//        Receiver receiver = new Receiver();
//        receiver.setUserId(userId);
//        receiver.setTargetUserTenantId(tenantId);
//
//        for (MessageRequest request : message){
//            Map<String, String> args = new HashMap<>();
//            args.put("userName", request.getUserName());
//            args.put("employeeNumber", request.getEmployeeNumber());
//            args.put("email", request.getEmail());
//            args.put("date", request.getDate());
//
//            Message message1 = messageClient.sendWebMessage(
//                    tenantId,
//                    TEMPlATE_CODE,
//                    LANGUAGE_CODE,
//                    Collections.singletonList(receiver),
//                    args);
//        }
//    }

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
            args.put("userName", request.getUserName());

            Message message1 = messageClient.sendWebMessage(tenantId, TEMPlATE_CODE, LANGUAGE_CODE,
                    Collections.singletonList(receiver),
                    args);
        }
    }
}
