package com.dingdong.lastdance_s.test;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController
{
    @GetMapping("/test")
    public Map<String, String> testGet()
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", "GET Test from Spring Boot");
        return response;
    }

    @PostMapping("/test")
    public Map<String, Object> testPost(@RequestBody Map<String, String> requestBody)
    {
        String input = requestBody.get("input"); // Extracting "input" from the JSON request
        Map<String, Object> response = new HashMap<>();
        response.put("message", "POST Test from Spring Boot");
        response.put("inputReceived", input);
        return response;
    }
}
