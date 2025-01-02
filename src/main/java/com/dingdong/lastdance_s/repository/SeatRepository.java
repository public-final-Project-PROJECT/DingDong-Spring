package com.dingdong.lastdance_s.repository;


import com.dingdong.lastdance_s.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByClassId(int classId);

    @Modifying
    @Query("UPDATE Seat s SET s.rowId = :rowId, s.columnId = :columnId WHERE s.classId = :classId AND s.studentId = :studentId")
    void updateSeatPosition(Integer classId, Integer studentId, Integer rowId, Integer columnId);
}
