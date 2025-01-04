package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.Class;
import com.dingdong.lastdance_s.model.ClassRequest;
import com.dingdong.lastdance_s.service.ClassService;
import com.dingdong.lastdance_s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;
    private final UserService userService;

    @Autowired
    public ClassController(ClassService classService, UserService userService) {
        this.classService = classService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createClass(@RequestBody ClassRequest request) {
        int userId = userService.findUserIdByEmail(request.getEmail());
        if (userId == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }

        int classCount = classService.getClassCountByTeacherId(userId);
        if (classCount >= 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only create up to 2 classes.");
        }

        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        String response = classService.createClass(request.getEmail(), request.getSchoolName(), request.getGrade(), request.getClassNo(), request.getClassNickname(), createTime);

        if (response.startsWith("Error:")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable int id) {
        return classService.getClassById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolName}")
    public ResponseEntity<List<Class>> getClassesBySchoolName(@PathVariable String schoolName) {
        List<Class> classes = classService.getClassesBySchoolName(schoolName);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/grade/{grade}/class/{classNo}/teacher/{teacherId}")
    public ResponseEntity<List<Class>> getClassesByGradeAndClassNo(@PathVariable int grade, @PathVariable int classNo, @PathVariable int teacherId) {
        List<Class> classes = classService.getClassesByGradeAndClassNoAndTeacherId(grade, classNo, teacherId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Class>> getClassesByTeacherId(@PathVariable int teacherId) {
        List<Class> classes = classService.getClassesByTeacherId(teacherId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/count/{teacherId}")
    public int getClassCount(@PathVariable int teacherId) {
        return classService.getClassCountByTeacherId(teacherId);
    }

    @Transactional
    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<Void> deleteClassByUserId(@PathVariable int teacherId) {
        classService.deleteClassByUserId(teacherId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/delete/{teacherId}/{classId}")
    public ResponseEntity<Void> deleteClassByUserIdAndClassId(@PathVariable int teacherId, @PathVariable int classId) {
        classService.deleteClassByUserIdAndId(teacherId, classId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{teacherId}/{classId}")
    public ResponseEntity<String> updateClassName(@PathVariable int teacherId, @PathVariable int classId, @RequestBody ClassRequest request) {
        boolean isUpdated = classService.updateClassName(teacherId, classId, request.getClassNickname());
        if (isUpdated) {
            return ResponseEntity.ok("Class name updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update class name.");
        }
    }
}
