package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Class;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {


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
}

