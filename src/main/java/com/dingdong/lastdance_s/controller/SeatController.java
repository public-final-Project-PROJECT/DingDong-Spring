package com.dingdong.lastdance_s.controller;
import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
        System.out.println("학급id 로 좌석테이블 조회 :: " + seatStudents);

        List<Map<String, Object>> newSeats = new ArrayList<>(); // 새로운 좌석 배치 리스트

        if(seatStudents.isEmpty()){
            // 만약 seat_table 이 없다면 해당 학급 학생들을 번호순으로 seat_table 에 넣어준다.
            List<Students> studentsList = seatService.findByClassId(classId);
            System.out.println("조회해온 " + classId +  "반 학생 list : " + studentsList);

            int row = 1; // 시작 행
            int column = 1; // 시작 열
            for(Students students : studentsList){
                Map<String, Object> seat = new HashMap<>();
                seat.put("studentId", students.getStudentId());
                seat.put("rowId", row);
                seat.put("columnId", column);
                newSeats.add(seat);

                // 5열이 넘으면 다음행으로 넘겨주기
                column++;
                if(column > 5){
                    column = 1;
                    row++;
                }
            }
            for (Map<String, Object> seat : newSeats) {
                System.out.println("Student ID: " + seat.get("studentId") +
                        ", Row: " + seat.get("rowId") +
                        ", Column: " + seat.get("columnId"));
            }

            List<Map<String, Object>> seatList = seatService.saveSeat(newSeats);

            if(seatList.size()>0){
                return ResponseEntity.ok(seatStudents);
            }
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

            List<Map<String, Object>> seatList = payload.get("studentList");
            if (seatList == null) {
                return ResponseEntity.badRequest().body("studentList null 로 넘아옴");
            }
            seatService.updateSeats(seatList);
            return ResponseEntity.ok("저장 성공 ");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("좌석 저장 실패 : " + e.getMessage());
        }
    }



}
