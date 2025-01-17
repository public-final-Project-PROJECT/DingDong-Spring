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
        List<Students> list =  studentsService.getStudentsByClassId(classId);
//      for (Students students : list) {
//          System.out.println(students);
//      }
        return ResponseEntity.ok(list);

    }

    @GetMapping("/viewClass")
    public ResponseEntity<List<StudentsDTO>> viewClass(@RequestParam("classId") int classId) {

        List<StudentsDTO> list =  studentsService.getStudentsByClass(classId);
        for (StudentsDTO students : list) {
            System.out.println(students);
        }
        return ResponseEntity.ok(list);

    }

    @GetMapping("viewClass/{studentId}")
    public  ResponseEntity<StudentsDTO> viewClassId
            (@PathVariable("studentId") int studentId) {


        StudentsDTO list =  studentsService.getStudentsAndClassByStudentId(studentId);
        System.out.println(list);

        return ResponseEntity.ok(list);
    }


    @GetMapping("view/{studentId}")
    public  ResponseEntity<List<Students>> viewDe
            (@PathVariable("studentId") int studentId) {

        List<Students> list =  studentsService.getStudentsByStudentId(studentId);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/updateMemo/{studentId}")
    public ResponseEntity<String> updateMemo(
            @PathVariable int studentId,
            @RequestBody String memo) {

        System.out.println("meno"+ memo);

        try {
            studentsService.updateMemo(studentId, memo);
            return ResponseEntity.ok("메모 업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("메모 업데이트 실패: " + e.getMessage());
        }


    }

    @PostMapping("/register/{studentId}")
    public ResponseEntity<String> registStudent(
            @PathVariable int studentId,
            @RequestParam("studentName") String studentName,
            @RequestParam("studentBirth") String studentBirth,
            @RequestParam("studentPhone") String studentPhone,
            @RequestParam("studentAddress") String studentAddress,
            @RequestParam("studentEtc") String studentEtc,
            @RequestParam("parentsName") String parentsName,
            @RequestParam("parentsPhone") String parentsPhone,
            @RequestParam("studentGender") String studentGender,
            @RequestParam(value = "studentImg", required = false) MultipartFile studentImg) {
        System.out.println("여기?");


        try {
            studentsService.updateData(studentId,studentName,studentBirth,studentPhone,studentAddress,studentEtc,parentsName,parentsPhone,studentGender,studentImg);
            return ResponseEntity.ok("업데이트 성공");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("업데이트 실패.");
        }

    }
}

