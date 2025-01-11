package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.SeatRepository;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
}
