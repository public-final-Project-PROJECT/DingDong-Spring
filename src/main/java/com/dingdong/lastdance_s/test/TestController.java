package com.dingdong.lastdance_s.test;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1557")
public class TestController
{
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
