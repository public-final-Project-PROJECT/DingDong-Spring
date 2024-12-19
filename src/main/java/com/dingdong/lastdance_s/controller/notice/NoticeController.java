package com.dingdong.lastdance_s.controller.notice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping("/view")
    public ResponseEntity<String> view() {
        return  ResponseEntity.ok("리스트");
    }

}



