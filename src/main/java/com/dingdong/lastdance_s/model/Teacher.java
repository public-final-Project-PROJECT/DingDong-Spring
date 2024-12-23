package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;

@Entity


public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_img")
    private String teacherImage;

    @Column(name = "teacher_email")
    private String teacherEmail;

    public Teacher() {
    }

    public Teacher(Long teacherId, String teacherName, String teacherImage, String teacherEmail) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherImage = teacherImage;
        this.teacherEmail = teacherEmail;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherImage() {
        return teacherImage;
    }

    public void setTeacherImage(String teacherImage) {
        this.teacherImage = teacherImage;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherImage='" + teacherImage + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                '}';
    }
}
