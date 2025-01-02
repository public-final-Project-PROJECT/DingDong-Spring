package com.dingdong.lastdance_s.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "teacher_name")
    private String name;

    @Column(name = "teacher_img")
    private String picture;

    @Column(name = "teacher_email", unique = true, nullable = false)
    private String email;

    @Column(name = "school_name")
    private String schoolName;
}

