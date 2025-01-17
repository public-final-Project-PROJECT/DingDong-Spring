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


    public List<AttendanceDTO> getTodayAttendance(int classId, LocalDate attendanceDate) {
        List<AttendanceDTO> list =   attendanceRepository.findByClassIdAndAttendanceDate(classId,attendanceDate);
        return list;

    }

    public void saveOrUpdateAttendances(List<AttendanceDTO> attendanceDTOList) {
        for (AttendanceDTO dto : attendanceDTOList) {
            Attendance existingAttendance = attendanceRepository
                    .findByStudentIdAndAttendanceDate(dto.getStudentId(), dto.getAttendanceDate());

            if (existingAttendance != null) {
                existingAttendance.setAttendanceState(dto.getAttendanceState());
                existingAttendance.setAttendanceEtc(dto.getAttendanceEtc());
                attendanceRepository.save(existingAttendance);
            } else {
                Attendance newAttendance = new Attendance();
                Students student = new Students();
                student.setStudentId(dto.getStudentId());
                newAttendance.setStudentId(student);
                newAttendance.setAttendanceDate(dto.getAttendanceDate());
                newAttendance.setAttendanceState(dto.getAttendanceState());
                newAttendance.setAttendanceEtc(dto.getAttendanceEtc());
                newAttendance.setClassId(dto.getClassId());
                attendanceRepository.save(newAttendance);
            }
        }
    }
}