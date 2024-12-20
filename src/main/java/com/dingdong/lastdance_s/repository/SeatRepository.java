package com.dingdong.lastdance_s.repository;


import com.dingdong.lastdance_s.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
