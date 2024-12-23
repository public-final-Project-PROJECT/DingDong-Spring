package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    int id = 1;

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/view")
    public ResponseEntity<List<Notice>> view() {

        List<Notice> list = noticeService.getNoticesByClassId(id);
        System.out.println("gk");
        for (Notice notice : list) {
            System.out.println(notice);
        }

        return  ResponseEntity.ok(list);
    }

    @PostMapping("hello")
    public ResponseEntity<String> hello() {
        return  ResponseEntity.ok("hello");
    }

}



