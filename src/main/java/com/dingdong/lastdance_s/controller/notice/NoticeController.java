package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    int id = 1;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/view")
    public ResponseEntity<List<Notice>> view() {

        List<Notice> list = noticeService.getNoticesByClassId(id);
        System.out.println("gk");
        for (Notice notice : list) {
            System.out.println(notice);
        }

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




