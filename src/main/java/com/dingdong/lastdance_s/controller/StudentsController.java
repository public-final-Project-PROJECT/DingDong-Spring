package com.dingdong.lastdance_s.controller;


import com.dingdong.lastdance_s.dto.StudentsDTO;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/view")
    public ResponseEntity<List<Students>> view(@RequestParam("classId") int classId) {
        List<Students> list = studentsService.getStudentsByClassId(classId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/viewClass")
    public ResponseEntity<List<StudentsDTO>> viewClass(@RequestParam("classId") int classId) {
        List<StudentsDTO> list = studentsService.getStudentsByClass(classId);
        return ResponseEntity.ok(list);

    }

    @GetMapping("viewClass/{studentId}")
    public ResponseEntity<StudentsDTO> viewClassId(@PathVariable("studentId") int studentId) {

        System.out.println( "학생"+studentId);
        StudentsDTO list = studentsService.getStudentsAndClassByStudentId(studentId);
        System.out.println(list);
        return ResponseEntity.ok(list);
    }


    @GetMapping("view/{studentId}")
    public ResponseEntity<List<Students>> viewDe(@PathVariable("studentId") int studentId) {
        List<Students> list = studentsService.getStudentsByStudentId(studentId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/updateMemo/{studentId}")
    public ResponseEntity<String> updateMemo(@PathVariable int studentId, @RequestBody String memo) {
        try {
            studentsService.updateMemo(studentId, memo);
            return ResponseEntity.ok("메모 업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메모 업데이트 실패: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestParam("studentNo") int studentNo, @RequestParam("studentName") String studentName, @RequestParam("classId") int classId) {
        try {
            studentsService.addStudent(studentNo, studentName, classId);
            return ResponseEntity.ok("User info saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get/class/{classId}/no/{studentNo}")
    public int getStudentByClassIdAndStudentNo(@PathVariable int classId, @PathVariable int studentNo) {
        return studentsService.getStudentByClassIdAndStudentNo(classId, studentNo);
    }

    @PostMapping("/register/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentId, @RequestParam("studentBirth") String studentBirth, @RequestParam("studentPhone") String studentPhone, @RequestParam("studentAddress") String studentAddress, @RequestParam("studentEtc") String studentEtc, @RequestParam("parentsName") String parentsName, @RequestParam("parentsPhone") String parentsPhone, @RequestParam("studentGender") String studentGender, @RequestParam(value = "studentImg", required = false) MultipartFile studentImg) {
        try {
            studentsService.updateData(studentId, studentBirth, studentPhone, studentAddress, studentEtc, parentsName, parentsPhone, studentGender, studentImg);
            return ResponseEntity.ok("업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

