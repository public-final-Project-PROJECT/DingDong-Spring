package com.dingdong.lastdance_s.controller;


import com.dingdong.lastdance_s.dto.StudentsDTO;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

