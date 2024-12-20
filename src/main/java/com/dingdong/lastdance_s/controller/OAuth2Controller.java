package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.Teacher;
import com.dingdong.lastdance_s.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login/oauth2/code")
public class OAuth2Controller {

    private final TeacherService teacherService;

    @Autowired
    public OAuth2Controller(TeacherService teacherService)
    {
        this.teacherService = teacherService;
    }

    @PostMapping("/google")
    public Map<String, Object> handleGoogleLogin(@RequestBody Map<String, String> request) {
        String googleToken = request.get("token");

        GoogleUser googleUser = verifyGoogleToken(googleToken);

        Teacher teacher = teacherService.processGoogleLogin(
                googleUser.getEmail(),
                googleUser.getName(),
                googleUser.getPicture(),
                googleToken
        );

        // Return response
        Map<String, Object> response = new HashMap<>();
        response.put("token", googleToken);
        response.put("user", teacher);

        return response;
    }

    private GoogleUser verifyGoogleToken(String token) {
        System.out.println("Received token: " + token);  // Log the token
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        GoogleUser googleUser = restTemplate.getForObject(url, GoogleUser.class);

        if (googleUser == null) {
            throw new RuntimeException("Invalid Google token");
        }

        return googleUser;
    }


}
