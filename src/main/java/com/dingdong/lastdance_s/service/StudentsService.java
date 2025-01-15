package com.dingdong.lastdance_s.service;


import com.dingdong.lastdance_s.dto.StudentsDTO;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
public class StudentsService {

    @Autowired
    NoticeService noticeService;


    @Autowired
    StudentsRepository studentsRepository;

    public List<Students> getStudentsByClassId(int classId) {
        return studentsRepository.findAllByClassId(classId);
    }

    public List<Students> getStudentsByStudentId(int studentId) {

        return  studentsRepository.findByStudentId(studentId);
    }

    public void updateMemo(int studentId, String memo) {
        Students student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
        student.setMemo(memo);
        studentsRepository.save(student);
    }

    public List<StudentsDTO> getStudentsByClass(int classId) {

        List<StudentsDTO> list = studentsRepository.findClassByClassId(classId);
        return list;
    }

    public StudentsDTO getStudentsAndClassByStudentId(int studentId) {

        StudentsDTO list = studentsRepository.findStudentsAndClassByStudentId(studentId);
        return list;
    }

    public void updateData(int studentId, String studentName, String studentBirth, String studentPhone, String studentAddress, String studentEtc, String parentsName, String parentsPhone, String studentGender, MultipartFile studentImg) throws IOException {

        Students students = studentsRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));

        students.setStudentName(studentName);
        students.setStudentBirth(Date.valueOf(studentBirth));
        students.setStudentPhone(studentPhone);
        students.setStudentAddress(studentAddress);
        students.setStudentEtc(studentEtc);
        students.setParentsName(parentsName);
        students.setParentsPhone(parentsPhone);
        students.setStudentGender(Students.GenderCategory.valueOf(studentGender));
        if (studentImg != null && !studentImg.isEmpty()) {
            System.out.println("noticeImg" + studentImg.getOriginalFilename()+studentImg);
            String imgPath = noticeService.saveFile(studentImg);
            students.setStudentImg(imgPath);
        }

        studentsRepository.save(students);
    }
}

