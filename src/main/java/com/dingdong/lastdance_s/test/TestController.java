package com.dingdong.lastdance_s.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> testEndpoint(@RequestBody Map<String, String> request) {
        String input = request.get("input");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Received input: " + input);
        return ResponseEntity.ok(response);
    }
}
