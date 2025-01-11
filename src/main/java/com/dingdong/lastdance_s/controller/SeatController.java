package com.dingdong.lastdance_s.controller;
import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.model.Students;
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

        List<Seat> seatStudents = seatService.findAll(classId);
        if(seatStudents.size()>0){
            return ResponseEntity.ok(seatStudents);
        }
        return ResponseEntity.status(404).body(null);
    }

    // classId 로 학생 이름 조회
    @PostMapping("/findName")
    public ResponseEntity<Object> findName(@RequestBody Map<String, Object> params){


        int classId = Integer.parseInt(params.get("classId").toString());
        List<Students> nameList = seatService.findName(classId);

        if(nameList.size()>0){
            return ResponseEntity.ok(nameList);
        }
        return ResponseEntity.status(404).body(null);

    }

    // 랜덤 돌리기 한 학생들 좌석 저장
    @PostMapping("/saveSeat")
    public ResponseEntity<String> updateSeats(@RequestBody Map<String, List<Map<String, Object>>> payload) {
        try {
            System.out.println("좌석 저장하러 넘어옴");
            List<Map<String, Object>> seatList = payload.get("studentList");
            if (seatList == null) {
                return ResponseEntity.badRequest().body("studentList null 로 넘아옴");
            }
            seatService.updateSeats(seatList);
            System.out.println("저장 성공함 ㅃㅃ!!!!!!!!");
            return ResponseEntity.ok("저장 성공 ");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("좌석 저장 실패 : " + e.getMessage());
        }
    }



}
