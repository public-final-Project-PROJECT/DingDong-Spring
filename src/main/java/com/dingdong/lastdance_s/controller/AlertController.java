package com.dingdong.lastdance_s.controller;
import com.dingdong.lastdance_s.dto.AlertDTO;
import com.dingdong.lastdance_s.entity.Alert;
import com.dingdong.lastdance_s.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAlert(@RequestBody AlertDTO alertDTO) {

        System.out.println("알림확인");
        System.out.println(alertDTO.toString());
        alertService.alertSave(alertDTO);


        return ResponseEntity.ok("");
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewAlert(@RequestParam("classId") int classId , @RequestParam("studentId") Integer studentId) {


        List<Alert> AlertList;
        System.out.println(studentId);

        if (studentId == null) {
            AlertList = alertService.findByClassId(classId);
        } else {
            AlertList = alertService.findByClassIOrStudentId(classId, studentId);
        }


        System.out.println("안녕");
        for (Alert alert : AlertList) {
            System.out.println(alert);
        }
        if (AlertList != null && !AlertList.isEmpty()) {
            return ResponseEntity.ok(AlertList);
        } else {
            return ResponseEntity.status(500).body("알람 불러오기 오류");
        }


    }
}