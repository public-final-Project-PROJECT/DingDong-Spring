package com.dingdong.lastdance_s.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SeatId;  // 좌석 번호

    @Column(name = "class_id")
    private int classId;  // 학급 고유 id

    @Column(name = "student_name")
    private String studentName;  // 학생 이름

    public Seat() {
    }

    public Seat(int seatId, int classId, String studentName) {
        SeatId = seatId;
        this.classId = classId;
        this.studentName = studentName;
    }

    public int getSeatId() {
        return SeatId;
    }

    public void setSeatId(int seatId) {
        SeatId = seatId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "SeatId=" + SeatId +
                ", classId=" + classId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
