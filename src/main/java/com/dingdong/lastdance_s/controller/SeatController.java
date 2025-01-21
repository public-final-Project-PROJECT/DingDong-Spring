package com.dingdong.lastdance_s.controller;
import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;


    @PostMapping("/findAllSeat")
    public ResponseEntity<Object> findAllSeat(@RequestBody Map<String, Object> params) {
        try {
            int classId = Integer.parseInt(params.get("classId").toString());
            List<Seat> seatStudents = seatService.findAll(classId);
            List<Map<String, Object>> newSeats = new ArrayList<>();

            if (seatStudents.isEmpty()) {
                List<Students> studentsList = seatService.findByClassId(classId);

                int row = 1;
                int column = 1;

                for (Students students : studentsList) {
                    Map<String, Object> seat = new HashMap<>();
                    seat.put("studentId", students.getStudentId());
                    seat.put("rowId", row);
                    seat.put("columnId", column);
                    seat.put("classId", students.getClassId());
                    newSeats.add(seat);

                    column++;
                    if (column > 5) {
                        column = 1;
                        row++;
                    }
                }

                seatService.insertSeats(newSeats);
                seatStudents = seatService.findAll(classId);
            }

            return ResponseEntity.ok(seatStudents);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("잘못된 classId 형식입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버 오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/findName")
    public ResponseEntity<Object> findName(@RequestBody Map<String, Object> params){


        int classId = Integer.parseInt(params.get("classId").toString());
        List<Students> nameList = seatService.findName(classId);

        nameList.sort(Comparator.comparingInt(Students::getStudentNo));

        if(nameList.size()>0){
            return ResponseEntity.ok(nameList);
        }
        return ResponseEntity.status(404).body(null);

    }

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
