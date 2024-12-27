package com.dingdong.lastdance_s.service;


import com.dingdong.lastdance_s.dto.AttendanceDTO;
import com.dingdong.lastdance_s.model.Attendance;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> registerAttendances(List<Attendance> attendanceList) {

        return attendanceRepository.saveAll(attendanceList);
    }

    public List<Attendance> convertDTOToEntity(List<AttendanceDTO> attendanceDTOList) {
        return attendanceDTOList.stream().map(dto -> {
            Attendance attendance = new Attendance();
            Students student = new Students();
            student.setStudentId(dto.getStudentId()); // 학생 ID 설정

            attendance.setStudentId(student);
            attendance.setAttendanceDate(dto.getAttendanceDate());
            attendance.setAttendanceState(dto.getAttendanceState());
            attendance.setAttendanceEtc(dto.getAttendanceEtc());
            attendance.setClassId(dto.getClassId());

            return attendance;
        }).collect(Collectors.toList());
    }

    public void saveAttendances(List<Attendance> attendanceList) {
        attendanceRepository.saveAll(attendanceList);

    }

    public List<Attendance> getTodayAttendance(int classId, LocalDate attendanceDate) {
        List<Attendance> list =   attendanceRepository.findAttendanceByClassIdAndDate(classId,attendanceDate);
        return list;

    }
}