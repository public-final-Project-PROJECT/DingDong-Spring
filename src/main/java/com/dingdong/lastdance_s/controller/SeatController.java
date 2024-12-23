package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;



    // 좌석표 테이블 조회
    @PostMapping("/findAllSeat")
    public ResponseEntity<Object> findAllSeat(@RequestParam("classId") int classId){

        System.out.println("classId 넘어옴 :: " + classId);
        List<Seat> seatStudents = seatService.findAll(classId);
        if(seatStudents.size()>0){
            return ResponseEntity.ok(seatStudents);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/test")
    public String test() {
        return "테스트다 ~!";
    }
}
