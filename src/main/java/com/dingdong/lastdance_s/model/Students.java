package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_img")
    private String studentImg;

    @Column(name = "student_phone")
    private String studentPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_gender")
    private GenderCategory studentGender;

    @Column(name = "student_etc")
    private String studentEtc;

    @Column(name = "student_birth" )
    private Date studentBirth;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "parents_name")
    private String parentsName;

    @Column(name = "parents_phone")
    private String parentsPhone;

    @Column(name = "memo")
    private String memo;

    @Column(name = "class_id")
    private int classId;


    public Students() {
    }

    public Students(int studentId, String studentName, String studentImg, String studentPhone, GenderCategory studentGender, String studentEtc, Date studentBirth, String studentAddress, String parentsName, String parentsPhone, String memo, int classId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentImg = studentImg;
        this.studentPhone = studentPhone;
        this.studentGender = studentGender;
        this.studentEtc = studentEtc;
        this.studentBirth = studentBirth;
        this.studentAddress = studentAddress;
        this.parentsName = parentsName;
        this.parentsPhone = parentsPhone;
        this.memo = memo;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public GenderCategory getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(GenderCategory studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentEtc() {
        return studentEtc;
    }

    public void setStudentEtc(String studentEtc) {
        this.studentEtc = studentEtc;
    }

    public Date getStudentBirth() {
        return studentBirth;
    }

    public void setStudentBirth(Date studentBirth) {
        this.studentBirth = studentBirth;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentImg='" + studentImg + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", studentGender=" + studentGender +
                ", studentEtc='" + studentEtc + '\'' +
                ", studentBirth=" + studentBirth +
                ", studentAddress='" + studentAddress + '\'' +
                ", parentsName='" + parentsName + '\'' +
                ", parentsPhone='" + parentsPhone + '\'' +
                ", memo='" + memo + '\'' +
                ", classId=" + classId +
                '}';
    }

    public enum GenderCategory {
        남,
        여
    }


}

