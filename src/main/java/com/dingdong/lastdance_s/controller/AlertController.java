package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAlert(@RequestBody AlertDTO alertDTO) {

        System.out.println(alertDTO.toString());
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

    // 미투표 학생 alert
    @PostMapping("votingUserAlertSave")
    public ResponseEntity<Object> votingUserAlertSave(@RequestBody Map<String, Object> voteData) {
        int votingId = (int) voteData.get("votingId");
        int classId = (int) voteData.get("classId");
        int studentId = (int) voteData.get("studentId");

        Alert alert = alertService.nonVotingAlertSave(classId, studentId, votingId);
        if (alert != null) {
            return ResponseEntity.ok(alert);
        } else {
            return ResponseEntity.status(500).body("알람 불러오기 오류");
        }
    }

    // 투표 결과 알림
    @PostMapping("votingResultAlert")
    public ResponseEntity<Object> votingResultAlert(@RequestBody Map<String, Object> voteData) {
        int votingId = (int) voteData.get("votingId");
        int classId = (int) voteData.get("classId");
        Alert alert = alertService.votingResultAlert(classId, votingId);
        if (alert != null) {
            return ResponseEntity.ok(alert);
        } else {
            return ResponseEntity.status(500).body("알람 불러오기 오류");
        }

    }
}