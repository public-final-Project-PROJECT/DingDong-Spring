package com.dingdong.lastdance_s.controller.notice;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class FileServingController {

    // 로컬 파일 저장 경로
    private String uploadPath = "C:/uploads";

    // 파일 서빙
//    @GetMapping("/uploads/{fileName}")
//    public FileSystemResource getFile(@PathVariable String fileName) {
//        System.out.println("이미지확인!");
//        File file = new File(uploadPath + "/" + fileName);
//        return new FileSystemResource(file);
//    }

    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            // 파일 경로
            File file = new File(uploadPath + "/" + fileName);

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // URL 인코딩 처리
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");

            // 파일의 확장자 확인
            String fileExtension = getFileExtension(fileName).toLowerCase();

            // 이미지 파일이면 이미지로 처리
            if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("gif") || fileExtension.equals("WebP") || fileExtension.equals("bmp") || fileExtension.equals("tif") || fileExtension.equals("tiff")  ||fileExtension.equals("SVG") )  {
                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // 필요에 따라 다른 이미지 타입을 설정
                        .body(resource);
            } else {
                // 일반 파일이면 다운로드 처리
                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"") // 다운로드용 헤더
                        .body(resource);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 확장자 추출 메서드
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1);
    }
    }