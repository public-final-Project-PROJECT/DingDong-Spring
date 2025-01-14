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


    // 좌석표 테이블 조회
    @PostMapping("/findAllSeat")
    public ResponseEntity<Object> findAllSeat(@RequestBody Map<String, Object> params) {
        try {
            System.out.println("처음 넘어온 값 ::: " + params);
            int classId = Integer.parseInt(params.get("classId").toString());
            List<Seat> seatStudents = seatService.findAll(classId);
            System.out.println("학급 ID로 좌석 테이블 조회 :: " + seatStudents.toString());

            List<Map<String, Object>> newSeats = new ArrayList<>(); // 새로운 좌석 배치 리스트

            if (seatStudents.isEmpty()) {
                // seat_table이 없을 경우, 학생 리스트로 새 좌석 생성
                List<Students> studentsList = seatService.findByClassId(classId);
                System.out.println("조회해온 " + classId + "반 학생 list : " + studentsList);

                int row = 1; // 시작 행
                int column = 1; // 시작 열

                for (Students students : studentsList) {
                    Map<String, Object> seat = new HashMap<>();
                    seat.put("studentId", students.getStudentId());
                    seat.put("rowId", row);
                    seat.put("columnId", column);
                    seat.put("classId", students.getClassId());
                    newSeats.add(seat);

                    // 5열이 넘으면 다음 행으로 이동
                    column++;
                    if (column > 5) {
                        column = 1;
                        row++;
                    }
                }
                System.out.println("생성된 좌석 데이터: " + newSeats);

                seatService.insertSeats(newSeats);
                seatStudents = seatService.findAll(classId);
            }

            // 이미 데이터가 있는 경우 반환
            return ResponseEntity.ok(seatStudents);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("잘못된 classId 형식입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버 오류 발생: " + e.getMessage());
        }
    }

    // classId 로 학생 이름 조회
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

    // 랜덤 돌리기 한 학생들 좌석 저장
    @PostMapping("/saveSeat")
    public ResponseEntity<String> updateSeats(@RequestBody Map<String, List<Map<String, Object>>> payload) {
        try {
            System.out.println("랜덤돌린 후 저장 :: " + payload.toString());

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
