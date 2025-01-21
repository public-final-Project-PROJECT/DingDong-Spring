package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.dto.StudentsDTO;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return studentsRepository.findByStudentId(studentId);
    }

    public void updateMemo(int studentId, String memo) {
        Students student = studentsRepository.findById(studentId).orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
        student.setMemo(memo);
        studentsRepository.save(student);
    }

    public List<StudentsDTO> getStudentsByClass(int classId) {
        return studentsRepository.findClassByClassId(classId);
    }

    public StudentsDTO getStudentsAndClassByStudentId(int studentId) {
        return studentsRepository.findStudentsAndClassByStudentId(studentId);
    }


    public void updateData(int studentId, String studentBirth, String studentPhone, String studentAddress, String studentEtc, String parentsName, String parentsPhone, String studentGender, MultipartFile studentImg) throws IOException {
        Students students = studentsRepository.findById(studentId).orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
        students.setStudentBirth(studentBirth);
        students.setStudentPhone(studentPhone);
        students.setStudentAddress(studentAddress);
        students.setStudentEtc(studentEtc);
        students.setParentsName(parentsName);
        students.setParentsPhone(parentsPhone);
        students.setStudentGender(Students.GenderCategory.valueOf(studentGender));
        if (studentImg != null && !studentImg.isEmpty()) {
            String imgPath = noticeService.saveFile(studentImg);
            students.setStudentImg(imgPath);
        }
        System.out.println(students.getStudentPhone());

        studentsRepository.save(students);
        studentsRepository.flush();
    }

    public void addStudent(int studentNo, String studentName, int classId) {
        Students students = new Students();
        students.setStudentNo(studentNo);
        students.setStudentName(studentName);
        students.setClassId(classId);
        studentsRepository.save(students);
    }

    public void updateToken(String token, Integer studentId) {
        Students students = studentsRepository.findById(studentId).orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
        students.setToken(token);
        studentsRepository.save(students);
    }


    public String findTokenByStudentId(Integer studentId) {
        return studentsRepository.findByToken(studentId);
    }

    public List<Integer> findStudentIdsByClassId(int classId) {
        return studentsRepository.findStudentIdByClassId(classId);
    }

    public int getStudentByClassIdAndStudentNo(int classId, int studentNo) {
        return studentsRepository.getStudentByClassIdAndStudentNo(classId, studentNo);
    }
}

