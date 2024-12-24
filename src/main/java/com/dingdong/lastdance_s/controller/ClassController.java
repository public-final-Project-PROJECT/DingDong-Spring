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
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can only create up to 2 classes.");
        }

        String response = classService.createClass(
                request.getEmail(),
                request.getSchoolName(),
                request.getGrade(),
                request.getClassNo(),
                request.getClassNickname()
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable int id) {
        return classService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/school/{schoolName}")
    public ResponseEntity<List<Class>> getClassesBySchoolName(@PathVariable String schoolName) {
        List<Class> classes = classService.getClassesBySchoolName(schoolName);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/grade/{grade}/class/{classNo}")
    public ResponseEntity<List<Class>> getClassesByGradeAndClassNo(@PathVariable int grade, @PathVariable int classNo) {
        List<Class> classes = classService.getClassesByGradeAndClassNo(grade, classNo);
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
    public ResponseEntity<Void> deleteClassById(@PathVariable int teacherId) {
        classService.deleteClassByUserId(teacherId);
        return ResponseEntity.noContent().build();
    }
}
