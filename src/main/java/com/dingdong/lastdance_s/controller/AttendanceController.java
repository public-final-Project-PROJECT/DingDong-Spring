package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.dto.AttendanceDTO;
import com.dingdong.lastdance_s.model.Attendance;
import com.dingdong.lastdance_s.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;


    @GetMapping("/view/{classId}")
    public ResponseEntity<List<AttendanceDTO>>getTodayAttendance(@PathVariable("classId") int classId,
                                                              @RequestParam("attendanceDate") LocalDate attendanceDate){
        List<AttendanceDTO> list = attendanceService.getTodayAttendance(classId,attendanceDate);
        return ResponseEntity.ok(list);

    }




    @PostMapping("/register")
    public ResponseEntity<String> registerAttendance(@RequestBody List<AttendanceDTO> attendanceDTOList) {

        try {
            attendanceService.saveOrUpdateAttendances(attendanceDTOList);
            return ResponseEntity.ok("출석 정보가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("출석 정보 저장에 실패했습니다.");
        }
    }
}