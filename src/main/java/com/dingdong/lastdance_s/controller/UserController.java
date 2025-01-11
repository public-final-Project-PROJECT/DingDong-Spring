package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.User;
import com.dingdong.lastdance_s.exceptions.UserNotFoundException;
import com.dingdong.lastdance_s.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public Integer findUserIdByEmail(@PathVariable String email) {
        return userService.findUserIdByEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleUserInfo(@RequestBody Map<String, Object> userInfo) {
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String picture = (String) userInfo.get("picture");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPicture(picture);

        userService.saveOrUpdateUser(user);

        return ResponseEntity.ok("User info saved successfully!");
    }

    @PostMapping("/add/school")
    public ResponseEntity<String> handleAddSchool(@RequestBody Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        String schoolName = (String) userInfo.get("schoolName");

        User user = new User();
        user.setEmail(email);
        user.setSchoolName(schoolName);

        userService.saveOrUpdateUser(user);

        return ResponseEntity.ok("User info saved successfully!");
    }

    @PostMapping("/add/class")
    public ResponseEntity<String> handleAddClass(@RequestBody Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        Integer latestClassId = (Integer) userInfo.get("latestClassId");

        User user = new User();
        user.setEmail(email);
        user.setLatestClassId(latestClassId);

        userService.saveOrUpdateUser(user);

        return ResponseEntity.ok("User info saved successfully!");
    }

    @GetMapping("/get/school/{email}")
    public ResponseEntity<?> getSchoolName(@PathVariable String email)
    {
        Optional<String> schoolName = userService.getSchoolNameByEmail(email);

        if (schoolName.isPresent())
        {
            return ResponseEntity.ok(Map.of("schoolName", schoolName.get()));
        }

        return ResponseEntity.ok(Map.of());
    }

    @GetMapping("/get/class/{email}")
    public ResponseEntity<?> getLatestClassId(@PathVariable String email)
    {
        Optional<Integer> latestClassId = userService.getLatestClassIdByEmail(email);

        if (latestClassId.isPresent())
        {
            return ResponseEntity.ok(Map.of("latestClassId", latestClassId.get()));
        }

        return ResponseEntity.ok(Map.of());
    }

    @DeleteMapping("/withdraw/{email}")
    public ResponseEntity<?> withdraw(@PathVariable String email) {
        try {
            userService.withdraw(email);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "error", "message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", "error", "message", "Unexpected error occurred"));
        }
    }
}