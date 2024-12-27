package com.dingdong.lastdance_s.dto;

import com.dingdong.lastdance_s.model.Attendance;

import java.time.LocalDate;

public class AttendanceDTO {
    private int studentId;
    private LocalDate attendanceDate;
    private Attendance.AttendanceState attendanceState;
    private String attendanceEtc;
    private int classId;

    public AttendanceDTO() {
    }

    public AttendanceDTO(int studentId, LocalDate attendanceDate, Attendance.AttendanceState attendanceState, String attendanceEtc, int classId) {
        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        this.attendanceState = attendanceState;
        this.attendanceEtc = attendanceEtc;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Attendance.AttendanceState getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(Attendance.AttendanceState attendanceState) {
        this.attendanceState = attendanceState;
    }

    public String getAttendanceEtc() {
        return attendanceEtc;
    }

    public void setAttendanceEtc(String attendanceEtc) {
        this.attendanceEtc = attendanceEtc;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "studentId=" + studentId +
                ", attendanceDate=" + attendanceDate +
                ", attendanceState=" + attendanceState +
                ", attendanceEtc='" + attendanceEtc + '\'' +
                ", classId=" + classId +
                '}';
    }
}
