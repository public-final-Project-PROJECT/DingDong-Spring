package com.dingdong.lastdance_s.repository;


import com.dingdong.lastdance_s.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByClassId(int classId);
}
