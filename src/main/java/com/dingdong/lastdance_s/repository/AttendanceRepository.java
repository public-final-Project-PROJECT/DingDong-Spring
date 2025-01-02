package com.dingdong.lastdance_s.repository;

import com.dingdong.lastdance_s.dto.AttendanceDTO;
import com.dingdong.lastdance_s.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {


    @Query("SELECT new com.dingdong.lastdance_s.dto.AttendanceDTO(a.attendanceId, s.studentId, s.studentName, a.attendanceDate, " +
            "a.attendanceState, a.attendanceEtc) " +
            "FROM Attendance a RIGHT JOIN Students s ON a.studentId = s " +
            "WHERE s.classId = :classId AND (a.attendanceDate = :attendanceDate OR a.attendanceDate IS NULL) ORDER BY s.studentName ASC ")
    List<AttendanceDTO> findByClassIdAndAttendanceDate(@Param("classId") int classId,
                                                       @Param("attendanceDate") LocalDate attendanceDate);

//    @Query("SELECT a FROM Attendance a WHERE a.studentId.studentId = :studentId AND a.attendanceDate = :attendanceDate")
//    Attendance findByStudentIdAndAttendanceDate(@Param("studentId") int studentId,
//                                                @Param("attendanceDate") LocalDate attendanceDate);

    @Query("SELECT a FROM Attendance a " +
            "WHERE a.studentId.studentId = :studentId AND a.attendanceDate = :attendanceDate " +
            "ORDER BY a.studentId.studentName ASC")
    Attendance findByStudentIdAndAttendanceDate(@Param("studentId") int studentId,
                                                @Param("attendanceDate") LocalDate attendanceDate);
}

