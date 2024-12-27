package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private  Students studentId;

    @Column(name = "attendance_state")
    @Enumerated(EnumType.STRING)
    private AttendanceState attendanceState;

    @Column(name = "attendance_etc")
    private String attendanceEtc;

    @Column(name = "attendance_date")
    //@CreationTimestamp // Hibernate 어노테이션: 생성 시점의 날짜를 자동으로 설정
    private LocalDate attendanceDate;

    @Column(name = "class_id")
    private int classId;

    public Attendance() {
    }

    public Attendance(int attendanceId, Students studentId, AttendanceState attendanceState, String attendanceEtc, LocalDate attendanceDate, int classId) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.attendanceState = attendanceState;
        this.attendanceEtc = attendanceEtc;
        this.attendanceDate = attendanceDate;
        this.classId = classId;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public AttendanceState getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(AttendanceState attendanceState) {
        this.attendanceState = attendanceState;
    }

    public String getAttendanceEtc() {
        return attendanceEtc;
    }

    public void setAttendanceEtc(String attendanceEtc) {
        this.attendanceEtc = attendanceEtc;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", attendanceState=" + attendanceState +
                ", attendanceEtc='" + attendanceEtc + '\'' +
                ", attendanceDate=" + attendanceDate +
                ", classId=" + classId +
                '}';
    }

    public enum AttendanceState {
        출석,
        결석,
        지각,
        조퇴
    }
}


