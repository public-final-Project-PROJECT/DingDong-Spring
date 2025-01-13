package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.SeatRepository;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private StudentsRepository studentsRepository;


    // 학생 좌석표 조회
    public List<Seat> findAll(int classId) {

        // 1. 넘겨받은 classId 에 해당되는 학생조회
        List<Seat> seats = seatRepository.findByClassId(classId);
        System.out.println("classID 로 조회 한 학생 :: " + seats);

        // 2.
        return seats;
    }

    public List<Students> findName(int classId) {

        List<Students> nameList = studentsRepository.findByClassId(classId);
        return nameList;
    }

    @Transactional
    public void updateSeats(List<Map<String, Object>> seatList) {
        for (Map<String, Object> seat : seatList) {
            Integer classId = (Integer) seat.get("classId");
            Integer studentId = (Integer) seat.get("studentId");
            Integer rowId = (Integer) seat.get("rowId");
            Integer columnId = (Integer) seat.get("columnId");

            seatRepository.updateSeatPosition(classId, studentId, rowId, columnId);
        }
    }

    // 학생들 조회하기
    public List<Students> findByClassId(int classId) {

        List<Students> studentsList = studentsRepository.findByClassId(classId);
        return studentsList;
    }

    // 좌석 저장
    public List<Map<String, Object>> saveSeat(List<Map<String, Object>> newSeats) {
        System.out.println("좌석 저장에 넘어온 newSeats :: " + newSeats);
        List<Map<String, Object>> savedSeats = new ArrayList<>();

        for (Map<String, Object> seatData : newSeats) {
            try {
                // studentId 변환
                Object studentIdObj = seatData.get("studentId");
                Integer studentId = (Integer) studentIdObj;


                // 데이터 가져오기
                int columnId = Integer.parseInt(seatData.get("columnId").toString());
                int rowId = Integer.parseInt(seatData.get("rowId").toString());
                int classId = 2; // 예제의 경우 고정값, 필요시 동적으로 처리

                // Seat 객체 생성 및 저장
                Seat seat = new Seat(classId, columnId, rowId, studentId);
                System.out.println("여기이이이 4개 찍혀야댐 :: " + seat.toString());
                Seat lastSeat = seatRepository.save(seat);

                // 저장된 좌석을 Map으로 변환
                Map<String, Object> seatMap = new HashMap<>();
                seatMap.put("seatId", lastSeat.getSeatId());
                seatMap.put("classId", lastSeat.getClassId());

                seatMap.put("columnId", lastSeat.getColumnId());
                seatMap.put("rowId", lastSeat.getRowId());
                seatMap.put("studentId", lastSeat.getStudentId());

                savedSeats.add(seatMap);
            } catch (Exception e) {
                System.out.println("좌석 저장 중 오류 발생: " + e.getMessage());
            }
        }

        return savedSeats;
    }

    public List<Seat> insertSeats(List<Map<String, Object>> newSeats) {
        System.out.println("새로 만든 seat 서비스 : " + newSeats);

        List<Seat> insertedSeats = new ArrayList<>();

        for (Map<String, Object> seatData : newSeats) {

            Seat seat = new Seat();
            seat.setClassId(Integer.parseInt(seatData.get("classId").toString()));
            seat.setColumnId(Integer.parseInt(seatData.get("columnId").toString()));
            seat.setRowId(Integer.parseInt(seatData.get("rowId").toString()));
            seat.setStudentId(Integer.parseInt(seatData.get("studentId").toString()));

            Seat savedSeat = seatRepository.save(seat);
            insertedSeats.add(savedSeat);
        }

        return insertedSeats;
    }
}

