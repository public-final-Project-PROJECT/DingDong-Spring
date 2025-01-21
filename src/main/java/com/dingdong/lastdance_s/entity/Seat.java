package com.dingdong.lastdance_s.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "seat_table")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SeatId;

    @Column(name = "class_id")
    private int classId;

    @Column(name = "column_id")
    private int columnId;

    @Column(name = "row_id")
    private int rowId;

    @Column(name = "student_id")
    private int studentId;

    public Seat() {
    }

    public Seat(int seatId, int classId, int columnId, int rowId, int studentId) {
        SeatId = seatId;
        this.classId = classId;
        this.columnId = columnId;
        this.rowId = rowId;
        this.studentId = studentId;
    }

    public Seat(int classId, int columnId, int rowId, int studentId) {
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

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "SeatId=" + SeatId +
                ", classId=" + classId +
                ", columnId=" + columnId +
                ", rowId=" + rowId +
                ", studentId=" + studentId +
                '}';
    }
}
