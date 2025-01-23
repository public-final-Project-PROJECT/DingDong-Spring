package com.dingdong.lastdance_s.controller.notice;


import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.model.Students;
import com.dingdong.lastdance_s.repository.AlertRepository;
import com.dingdong.lastdance_s.service.AlertService;
import com.dingdong.lastdance_s.service.NoticeService;
import com.dingdong.lastdance_s.service.StudentsService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {


    @Autowired
    private NoticeService noticeService;

    private String uploadPath = "C:/uploads";

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping("/view")
    public ResponseEntity<List<Notice>> view(
            @RequestParam("classId") int classId,
            @RequestParam(value = "noticeCategory", required = false) Notice.NoticeCategory noticeCategory) {
        List<Notice> list;
        if (noticeCategory != null) {
            list = noticeService.getNoticesByClassIdAndCategory(classId, noticeCategory);
        } else {
            list = noticeService.getNoticesByClassId(classId);
        }

        return ResponseEntity.ok(list);
    }


    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<List<Notice>> viewDetail(@PathVariable int noticeId) {
        List<Notice> list = noticeService.getNoticeIdByNotice(noticeId);
        return ResponseEntity.ok(list);

    }


    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> insertNotice(
            @RequestParam("noticeTitle") String noticeTitle,
            @RequestParam("noticeCategory") Notice.NoticeCategory noticeCategory,
            @RequestParam("noticeContent") String noticeContent,
            @RequestParam(value = "noticeImg", required = false) MultipartFile noticeImg,
            @RequestParam(value = "noticeFile", required = false) MultipartFile noticeFile,
            @RequestParam("classId") int classId
    ) {
        try {
            int noticeId = noticeService.saveNotice(noticeTitle, noticeCategory, noticeContent, noticeImg, noticeFile, classId);
            System.out.println(noticeFile);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "공지사항이 등록되었습니다.");
            response.put("noticeId", noticeId);



           List<Integer>studentList =  studentsService.findStudentIdsByClassId(classId);

            for (Integer studentId : studentList) {

                String token = studentsService.findTokenByStudentId(studentId);

                // token이 존재하면 알림 전송
                if (token != null && !token.isEmpty()) {





                    try {
                        Message message = Message.builder()
                                .setToken(token)
                                .setNotification(Notification.builder()
                                        .setTitle("공지사항")
                                        .setBody("새로운 공지사항이 등록되었습니다.")
                                        .build())
                                .build();
                        String responseMessage = FirebaseMessaging.getInstance().send(message);
                        System.out.println("알림 전송 성공: " + responseMessage);
                    } catch (FirebaseMessagingException e) {
                        System.err.println("알림 전송 실패 (학생 ID: " + studentId + "): " + e.getMessage());
                    }
                } else {
                    System.err.println("유효하지 않은 토큰 (학생 ID: " + studentId + ")");
                }
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(500).body(Map.of("message", "공지사항 등록에 실패했습니다."));
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

            Notice existingNotice = noticeService.getNoticeById(noticeId);

            existingNotice.setNoticeTitle(noticeTitle);
            existingNotice.setNoticeCategory(noticeCategory);
            existingNotice.setNoticeContent(noticeContent);


            if (noticeImg != null && !noticeImg.isEmpty()) {
                String imgPath = noticeService.saveFile(noticeImg);  // saveFile 호출
                existingNotice.setNoticeImg(imgPath);
            }

            if (noticeFile != null && !noticeFile.isEmpty()) {
                String filePath = noticeService.saveFile(noticeFile);  // saveFile 호출
                existingNotice.setNoticeFile(filePath);
            } else {

                String currentFilePath = existingNotice.getNoticeFile();
                if (currentFilePath != null && !currentFilePath.isEmpty()) {

                    existingNotice.setNoticeFile(currentFilePath);
                }
            }


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

           int alertId =  alertRepository.alertDelete(noticeId);
             alertRepository.deleteById(alertId);


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

    @PostMapping("/hi")
    public ResponseEntity<?> hi(@RequestBody Map<String, Object> notice) {

        Integer studentId = (Integer) notice.get("studentId");

        String token = studentsService.findTokenByStudentId(studentId);
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(Notification.builder()
                            .setTitle("공지사항")
                            .setBody("작성이 되었습니다!!!!!")
                            .build()).build();
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if (token != null && !token.isEmpty()) {
            return ResponseEntity.ok("Token registered.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }

    }


}
