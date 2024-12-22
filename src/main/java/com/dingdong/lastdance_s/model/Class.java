package com.dingdong.lastdance_s.model;


import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "grade_name")
    private int grade;

    @Column(name = "class_name")
    private int className;

    @Column(name = "class_nickname")
    private String classNickname;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    
}
