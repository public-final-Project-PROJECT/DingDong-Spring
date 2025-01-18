package com.dingdong.lastdance_s.controller.firebase;

import com.dingdong.lastdance_s.service.AlertService;
import com.dingdong.lastdance_s.service.StudentsService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fcm")
public class FireBaseController {

    @Autowired
    StudentsService studentsService;

    @PostMapping("/register-token")
    public ResponseEntity<String> registerToken(@RequestBody Map<String,Object> tokenRequest) {
        String token = (String) tokenRequest.get("token");
        Integer studentId = (Integer) tokenRequest.get("studentId");
        studentsService.updateToken(token,studentId);

        System.out.println(token);

        try{
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(Notification.builder()
                            .setTitle("테스트알림")
                            .setBody("서버측 테스트 메시지")
                            .build()).build();
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
        }catch (FirebaseMessagingException e){
            e.printStackTrace();
        }

        if (token != null && !token.isEmpty()) {
            return ResponseEntity.ok("Token registered.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
    }
}