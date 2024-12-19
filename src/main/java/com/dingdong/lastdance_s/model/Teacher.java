package com.dingdong.lastdance_s.model;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_img")
    private String teacherImage;

    @Column(name = "teacher_email")
    private String teacherEmail;
}
