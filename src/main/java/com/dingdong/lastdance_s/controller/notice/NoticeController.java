package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    int classId = 1;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/view")
    public ResponseEntity<List<Notice>> view() {
        List<Notice> list = noticeService.getNoticesByClassId(classId);
//        for (Notice notice : list) {
//            System.out.println(notice);
//        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<List<Notice>> viewDetail(@PathVariable int noticeId) {

        List<Notice> list = noticeService.getNoticeIdByNotice(noticeId);
        return ResponseEntity.ok(list);

    }


    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody Notice notice) {

        return ResponseEntity.ok("등록 완료");
    }


    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Notice notice) {

        return ResponseEntity.ok("수정완료");
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Notice notice) {
        return ResponseEntity.ok("삭제 완료");
    }

}




