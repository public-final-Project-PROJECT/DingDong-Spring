package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    int id = 1;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/view")
    public ResponseEntity<Object> view() {

        List<Notice> list = new ArrayList<>();
        list =  noticeService.getNoticesByClassId(id);

        return  ResponseEntity.ok(list);
    }

}



