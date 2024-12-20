package com.dingdong.lastdance_s.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_img")
    private String teacherImg;

    @Column(name = "teacher_email", nullable = false, unique = true)
    private String teacherEmail;

    @Column(name = "google_token")
    private String googleToken;
}
