package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Class;
import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    //int classId = 1;

    @Autowired
    private NoticeService noticeService;

    private String uploadPath = "C:/uploads";  // 실제 경로로 수정하세요


    @GetMapping("/view")
    public ResponseEntity<List<Notice>> view(@RequestParam("classId") int classId) {
        List<Notice> list = noticeService.getNoticesByClassId(classId);

//        for (Notice notice : list) {
//            System.out.println(notice);
//        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<List<Notice>> viewDetail(@PathVariable int noticeId){

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


    @PostMapping("/update/{noticeId}")
    public ResponseEntity<String> updateNotice(
            @PathVariable("noticeId") int noticeId,
            @RequestParam("noticeTitle") String noticeTitle,
            @RequestParam("noticeCategory") Notice.NoticeCategory noticeCategory,
            @RequestParam("noticeContent") String noticeContent,
            @RequestParam(value = "noticeImg", required = false) MultipartFile noticeImg,
            @RequestParam(value = "noticeFile", required = false) MultipartFile noticeFile
    ) {
        try {
            // 먼저 noticeId에 해당하는 공지사항을 DB에서 찾음
            Notice existingNotice = noticeService.getNoticeById(noticeId);

            // 수정할 값을 설정
            existingNotice.setNoticeTitle(noticeTitle);
            existingNotice.setNoticeCategory(noticeCategory);
            existingNotice.setNoticeContent(noticeContent);

            // 이미지 파일 처리
            if (noticeImg != null && !noticeImg.isEmpty()) {
                String imgPath = noticeService.saveFile(noticeImg);  // saveFile 호출
                existingNotice.setNoticeImg(imgPath);
            }

            // 파일 처리
            if (noticeFile != null && !noticeFile.isEmpty()) {
                String filePath = noticeService.saveFile(noticeFile);  // saveFile 호출
                existingNotice.setNoticeFile(filePath);
            }

            // 공지사항 업데이트
            noticeService.updateNotice(existingNotice);

            return ResponseEntity.ok("공지사항이 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("공지사항 수정에 실패했습니다.");
        }
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



