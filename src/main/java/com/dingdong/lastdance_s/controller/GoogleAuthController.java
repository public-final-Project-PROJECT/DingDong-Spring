package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2/authorization/google")
public class GoogleAuthController {

    private final UserService userService;

    public GoogleAuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> handleGoogleAuth(@RequestBody GoogleAuthRequest request) {
        // Validate token if necessary (e.g., using Google's Token Verification API)
        // Decode and save the user
        userService.saveUser(request.getUser());
        return ResponseEntity.ok("User stored successfully");
    }
}
