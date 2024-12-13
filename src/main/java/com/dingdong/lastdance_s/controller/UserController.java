package com.dingdong.lastdance_s.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @GetMapping("/user")
    public Map<String, Object> getUser(Authentication authentication) {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", oauth2User.getAttribute("name"));
        userInfo.put("email", oauth2User.getAttribute("email"));
        userInfo.put("picture", oauth2User.getAttribute("picture"));
        return userInfo;
    }

    @CrossOrigin(origins = "http://localhost:1557")
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Logout from OAuth2
        if (authentication != null) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            // Optionally, you can log the user's name or other details for auditing purposes
            System.out.println("Logging out user: " + oauth2User.getAttribute("name"));

            // Invalidate the session
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        // Optionally, clear user-related data
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", null);
        userInfo.put("email", null);
        userInfo.put("picture", null);

        // Redirect to home page or login page after logout
        response.setHeader("Location", "/");  // Change to the page you want to redirect after logout
        response.setStatus(HttpServletResponse.SC_FOUND); // 302 redirect status

        return userInfo;
    }
}
