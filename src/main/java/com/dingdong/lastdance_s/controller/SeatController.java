package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;



    // 좌석표 테이블 조회
    @PostMapping("/findAllSeat")
    public ResponseEntity<Object> findAllSeat(@RequestBody Map<String, Object> params){

        int classId = Integer.parseInt(params.get("classId").toString());
        System.out.println("classId 넘어옴 :: " + classId);
        List<Seat> seatStudents = seatService.findAll(classId);
        if(seatStudents.size()>0){
            return ResponseEntity.ok(seatStudents);
        }
        return ResponseEntity.status(404).body(null);
    }

//    // studentId 로 학생 이름 조회
//    @PostMapping("/findName")
//    public ResponseEntity<Object> findName(@RequestBody Map<String, Object> params){
//        List<Object> studentsId
//    }
}
