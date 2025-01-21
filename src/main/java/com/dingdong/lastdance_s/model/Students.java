package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
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
    private String studentBirth;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "parents_name")
    private String parentsName;

    @Column(name = "parents_phone")
    private String parentsPhone;

    @Column(name = "memo")
    private String memo;

    @Column(name = "student_no")
    private int studentNo;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "token")
    private String token;

    public Students() {
    }

    public Students(int studentId, String studentName, String studentImg, String studentPhone, GenderCategory studentGender, String studentEtc, String studentBirth, String studentAddress, String parentsName, String parentsPhone, String memo, int studentNo, int classId) {
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
        this.studentNo = studentNo;
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
                ", studentNo=" + studentNo +
                ", classId=" + classId +
                ", token='" + token + '\'' +
                '}';
    }

    public enum GenderCategory {
        남,
        여
    }
}

