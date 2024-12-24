package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.model.Class;
import com.dingdong.lastdance_s.model.Notice;
import com.dingdong.lastdance_s.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class NoticeService {

    //로컬용 파일 업로드
    @Value("${file.upload.path}")
    private String uploadPath;

    //배포용 파일업로드
    @Value("${file.upload.server-url}")
    private String serverUrl;

    @Autowired
    NoticeRepository noticeRepository;


    public List<Notice> getNoticesByClassId(int classId) {

        return noticeRepository.findAllByClassId(classId);
    }

    public List<Notice> getNoticeIdByNotice(int noticeId) {

        return noticeRepository.findAllByNoticeId(noticeId);
    }

    public Notice saveNotice(String noticeTitle, Notice.NoticeCategory noticeCategory, String noticeContent,
                             MultipartFile noticeImg, MultipartFile noticeFile, int classId) throws IOException {
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeCategory(noticeCategory);
        notice.setNoticeContent(noticeContent);
        notice.setClassId(classId);


      //
        // 이미지 파일 저장
        if (noticeImg != null && !noticeImg.isEmpty()) {
            System.out.println("noticeImg" + noticeImg.getOriginalFilename()+noticeImg);
            String imgPath = saveFile(noticeImg);
            notice.setNoticeImg(imgPath);
        }

        // 첨부 파일 저장
        if (noticeFile != null && !noticeFile.isEmpty()) {
            String filePath = saveFile(noticeFile);
            notice.setNoticeFile(filePath);
        }

        return noticeRepository.save(notice);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = uploadPath; // 업로드 경로
        File directory = new File(uploadDir);

        // 업로드 디렉토리가 존재하지 않으면 생성
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (dirCreated) {
                // 디렉토리 생성 후 쓰기 권한 부여
                directory.setWritable(true);
                directory.setReadable(true);
                System.out.println("Directory created and write permission granted: " + uploadDir);
            } else {
                throw new IOException("Failed to create directory: " + uploadDir);
            }
        }

        // 파일 이름 생성 (UUID + 원래 파일 이름)
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 경로 구분자 자동 처리
        String filePath = uploadDir + File.separator + fileName;

        // 파일 저장 위치 설정
        File destinationFile = new File(filePath);

        // 파일에 쓰기 권한 부여
        destinationFile.setWritable(true);
        destinationFile.setReadable(true);

        // 파일을 지정된 위치로 전송
        file.transferTo(destinationFile);

        // 로컬/배포 환경에 따른 URL 생성
        if (serverUrl == null || serverUrl.isEmpty()) {
            // 로컬 환경 (예: "C:/uploads/...")
            return "/uploads/" + fileName;  // 파일 경로를 URL로 변경
        } else {
            // 배포 환경 (예: "https://yourdomain.com/uploads/...")
            return serverUrl + "/uploads/" + fileName;  // 파일 경로를 서버 URL로 변경
        }
    }

    public boolean deleteNotice(int noticeId) {
        if (noticeRepository.existsById(noticeId)) {
            noticeRepository.deleteById(noticeId);
            return true;
        }
        return false;
    }
    }
