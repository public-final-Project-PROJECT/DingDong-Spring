package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    int classId = 1;

    @Autowired
    private NoticeService noticeService;

    private String uploadPath = "C:/uploads";  // 실제 경로로 수정하세요


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
//        for (Notice notice : list) {
//            System.out.println(notice);
//        }
        return ResponseEntity.ok(list);

    }


    @PostMapping("/insert")
    public ResponseEntity<String> insertNotice(
            @RequestParam("noticeTitle") String noticeTitle,
            @RequestParam("noticeCategory") Notice.NoticeCategory noticeCategory,
            @RequestParam("noticeContent") String noticeContent,
            @RequestParam(value = "noticeImg", required = false) MultipartFile noticeImg,
            @RequestParam(value = "noticeFile", required = false) MultipartFile noticeFile,
            @RequestParam("classId") int classId
    ) {
        try {
            noticeService.saveNotice(noticeTitle, noticeCategory, noticeContent, noticeImg, noticeFile,classId);
            return ResponseEntity.ok("공지사항이 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("공지사항 등록에 실패했습니다.");
        }
    }


    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Notice notice) {

        return ResponseEntity.ok("수정완료");
    }

    @PostMapping("/delete/{noticeId}")
    public ResponseEntity<Object> delete(@PathVariable("noticeId") int noticeId) {
        try {
            // 서비스에서 삭제 메서드 호출
            boolean isDeleted = noticeService.deleteNotice(noticeId);

            if (isDeleted) {
                return ResponseEntity.ok("삭제 완료");
            } else {
                return ResponseEntity.status(404).body("공지사항을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류, 삭제에 실패했습니다.");
        }
    }
}



