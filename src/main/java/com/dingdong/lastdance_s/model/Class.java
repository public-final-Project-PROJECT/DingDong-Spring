package com.dingdong.lastdance_s.model;


import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "grade_name")
    private int grade;

    @Column(name = "class_name")
    private int className;

    @Column(name = "class_nickname")
    private String classNickname;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacherId;

    public Class() {
    }

    public Class(int classId, String schoolName, int grade, int className, String classNickname, Teacher teacherId) {
        this.classId = classId;
        this.schoolName = schoolName;
        this.grade = grade;
        this.className = className;
        this.classNickname = classNickname;
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassName() {
        return className;
    }

    public void setClassName(int className) {
        this.className = className;
    }

    public String getClassNickname() {
        return classNickname;
    }

    public void setClassNickname(String classNickname) {
        this.classNickname = classNickname;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", schoolName='" + schoolName + '\'' +
                ", grade=" + grade +
                ", className=" + className +
                ", classNickname='" + classNickname + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}