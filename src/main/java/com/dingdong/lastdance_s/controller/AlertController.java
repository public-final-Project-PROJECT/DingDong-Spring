package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.entity.voting.Voting;
import com.dingdong.lastdance_s.entity.voting.VotingContents;
import com.dingdong.lastdance_s.service.AlertService;
import com.dingdong.lastdance_s.service.StudentsService;
import com.dingdong.lastdance_s.service.VotingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private StudentsService studentsService;
    @Autowired
    private VotingService votingService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAlert(@RequestBody AlertDTO alertDTO) {

        alertService.alertSave(alertDTO);
        return ResponseEntity.ok("");
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewAlert(@RequestParam("classId") int classId, @RequestParam("studentId") Integer studentId) {


        List<Alert> AlertList;
        if (studentId == null) {
            AlertList = alertService.findByClassId(classId);
        } else {
            AlertList = alertService.findByClassIOrStudentId(classId, studentId);
        }

        for (Alert alert : AlertList) {
            System.out.println(alert);
        }
        if (!AlertList.isEmpty()) {
            return ResponseEntity.ok(AlertList);
        } else {
            return ResponseEntity.status(500).body("알람 불러오기 오류");
        }


    }

    @GetMapping("/update")
    public ResponseEntity<?> updateAlert(@RequestParam("alertId") int alertId) {
        alertService.alertUpdate(alertId);
        return ResponseEntity.ok("읽음 처리 완료");

    }


    @PostMapping("votingUserAlertSave")
    public ResponseEntity<Object> votingUserAlertSave(@RequestBody Map<String, Object> voteData) {
        int votingId = (int) voteData.get("votingId");
        int classId = (int) voteData.get("classId");
        int studentId = (int) voteData.get("studentId");

        Alert alert = alertService.nonVotingAlertSave(classId, studentId, votingId);
        Voting voting = (Voting) votingService.findByClassIdAndVotingName(votingId);
        String title = voting.getVotingName();

        String token = studentsService.findTokenByStudentId(studentId);

        if (token != null && !token.isEmpty()) {
            try {
                Message message = Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder()
                                .setTitle("투표")
                                .setBody( title + " 투표를 아직 하지 않았습니다. 서둘러주세요!")
                                .build()).build();
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println(response);
            } catch (FirebaseMessagingException e) {
                System.err.println("알림 전송 실패 (학생 ID: " + studentId + "): " + e.getMessage());
            }
        }
           else{
                System.err.println("유효하지 않은 토큰 (학생 ID: " + studentId + ")");
            }
           return ResponseEntity.ok(alert);

    }

    // 투표 결과 알림
    @PostMapping("votingResultAlert")
    public ResponseEntity<Object> votingResultAlert(@RequestBody Map<String, Object> voteData) {
        int votingId = (int) voteData.get("votingId");
        int classId = (int) voteData.get("classId");
        System.out.println(votingId);
        System.out.println(classId);

        List<Integer> studentList =  studentsService.findStudentIdsByClassId(classId);
        Alert alert = alertService.votingResultAlert(classId, votingId);
        Voting voting = (Voting) votingService.findByClassIdAndVotingName(votingId);
        String title = voting.getVotingName();


        for (Integer studentId : studentList) {

            String token = studentsService.findTokenByStudentId(studentId);

            if (token != null && !token.isEmpty()) {
                try {
                    Message message = Message.builder()
                            .setToken(token)
                            .setNotification(Notification.builder()
                                    .setTitle("투표")
                                    .setBody(title + " 투표가 종료되었습니다. 결과를 확인하세요 !")
                                    .build()).build();
                    String response = FirebaseMessaging.getInstance().send(message);
                    System.out.println(response);
                } catch (FirebaseMessagingException e) {
                    System.err.println("알림 전송 실패 (학생 ID: " + studentId + "): " + e.getMessage());
                }
            }
            else{
                System.err.println("유효하지 않은 토큰 (학생 ID: " + studentId + ")");
            }
        }


        return ResponseEntity.ok(alert);

    }
}