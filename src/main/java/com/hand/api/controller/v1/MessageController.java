package com.hand.api.controller.v1;

import com.hand.api.controller.DTO.MessageRequest;
import com.hand.app.service.impl.MessageService;
import com.hand.config.SwaggerTags;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = SwaggerTags.MESSAGE)
@RestController("MessageController.v1")
@RequestMapping("/v1/{organizationId}/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "message")
    @PostMapping
    public ResponseEntity<?> sendEmail(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "email_receiver")String emailReceiver
            ,@RequestBody String context) {
        messageService.sendEmail(context, emailReceiver, tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(
                "success"
        );
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "Messenger Sent")
    @PostMapping("/email")
    public ResponseEntity<?> sendNotifications(
            @PathVariable("organizationId") Long tenantId,
            @RequestParam(name = "receiver") Long receiver,
            @RequestBody List<MessageRequest> messageRequests
    ){
        messageService.sendNotification(tenantId, receiver, messageRequests);
        return ResponseEntity.status(HttpStatus.OK).body("Succes");
    }
//    public ResponseEntity<?> sendNotifications(
//            @PathVariable("organizationId") Long tenantId,
//            @RequestParam(name = "receiver") Long receiver,
//            @RequestBody List<MessageRequest> messages) {
//        messageService.sendNotification(tenantId, receiver, messages);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                "success"
//        );
//    }
}
