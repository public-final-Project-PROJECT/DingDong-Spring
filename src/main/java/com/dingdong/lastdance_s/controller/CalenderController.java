package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.model.Calender;
import com.dingdong.lastdance_s.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/calendar")
public class CalenderController {

    @Autowired
    CalenderService calenderService;

    @PostMapping("/list")
    public ResponseEntity<Object> CalenderList(@RequestBody Map<String, Integer> requestBody) {
        // 요청 본문에서 input 값 가져오기
        int calenderId = requestBody.get("input");

        // 서비스에서 해당 id로 데이터를 가져옴
        Calender response = calenderService.CalenderList(calenderId);

        System.out.println(response);
        return ResponseEntity.ok(response);
    }

}
