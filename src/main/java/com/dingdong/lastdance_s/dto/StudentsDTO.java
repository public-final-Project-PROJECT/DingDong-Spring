package com.dingdong.lastdance_s.dto;

import com.dingdong.lastdance_s.model.Students;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StudentsDTO {
    private int studentId;
    private String studentName;
    private String studentImg;
    private String studentPhone;
    private Students.GenderCategory studentGender;
    private String studentEtc;
    private String studentBirth;
    private String studentAddress;
    private String parentsName;
    private String parentsPhone;
    private String memo;
    private int classId;
    private String schoolName;
    private int grade;
    private int classNo;
    private int studentNo;

    public StudentsDTO(int studentId, String studentName, String studentImg, String studentPhone, Students.GenderCategory studentGender, String studentEtc, String studentBirth, String studentAddress, String parentsName, String parentsPhone, String memo, int classId, String schoolName, int grade, int classNo, int studentNo) {
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
}
