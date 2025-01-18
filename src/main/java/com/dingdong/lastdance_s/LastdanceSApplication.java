package com.dingdong.lastdance_s;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// @EnableScheduling
@SpringBootApplication
public class LastdanceSApplication {

    public static void main(String[] args) {

        SpringApplication.run(LastdanceSApplication.class, args);
    }
    /*
     *   Firebase 를 초기화 하는 메소드
     * spring boot 어플리케이션이 시작될때 실행된다.
     * Firebase Admin SDk를 설정하고 FirebaseApp을 초기화한다.
     *
     * 그대로 쓰면 된다.!!
     * */
    @PostConstruct
    public void initFirebase() {
        try {
            // Firebase 앱이 이미 초기화되었는지 확인
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount = new FileInputStream(
                        "src/main/resources/dingdong-student-firebase-adminsdk-fbsvc-bd1fe77779.json"
                );
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("파이어베이스 초기화 완료");
            } else {
                System.out.println("FirebaseApp이 이미 초기화되어 있습니다.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Firebase 초기화 실패: JSON 파일을 찾을 수 없습니다.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Firebase 초기화 실패: JSON 파일 로드 중 오류 발생.");
            throw new RuntimeException(e);
        }
    }
}

