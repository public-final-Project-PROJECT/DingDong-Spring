package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Seat;
import com.dingdong.lastdance_s.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;


    // 학생 좌석표 조회
    public List<Seat> findAll(int classId) {

        // 1. 넘겨받은 classId 에 해당되는 학생조회
        List<Seat> seats = seatRepository.findByClassId(classId);
        System.out.println("classID 로 조회 한 학생 :: " + seats);

        // 2.
        return seats;
    }
}
