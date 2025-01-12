package com.dingdong.lastdance_s.dto;

import com.dingdong.lastdance_s.model.Students;

import java.util.Date;

public class StudentsDTO {
    private int studentId;
    private String studentName;
    private String studentImg;
    private String studentPhone;
    private Students.GenderCategory studentGender;
    private String studentEtc;
    private Date studentBirth;
    private String studentAddress;
    private String parentsName;
    private String parentsPhone;
    private String memo;
    private int classId;
    private String schoolName;
    private int grade;
    private int classNo;
    private int studentNo;

    public StudentsDTO() {
    }

    public StudentsDTO(int studentId, String studentName, String studentImg, String studentPhone, Students.GenderCategory studentGender, String studentEtc, Date studentBirth, String studentAddress, String parentsName, String parentsPhone, String memo, int classId, String schoolName, int grade, int classNo, int studentNo) {
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
        this.schoolName = schoolName;
        this.grade = grade;
        this.classNo = classNo;
        this.studentNo = studentNo;
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

    public Students.GenderCategory getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(Students.GenderCategory studentGender) {
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

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public String toString() {
        return "StudentsDTO{" +
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
                ", schoolName='" + schoolName + '\'' +
                ", grade=" + grade +
                ", classNo=" + classNo +
                ", studentNo=" + studentNo +
                '}';
    }
}
