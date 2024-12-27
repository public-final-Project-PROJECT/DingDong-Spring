package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.classId = :classId AND a.attendanceDate = :attendanceDate")
    List<Attendance> findAttendanceByClassIdAndDate(@Param("classId") int classId, @Param("attendanceDate") LocalDate attendanceDate);
}

