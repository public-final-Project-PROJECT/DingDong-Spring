package com.dingdong.lastdance_s.model;


import com.dingdong.lastdance_s.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "grade_no")
    private int grade;

    @Column(name = "class_no")
    private int classNo;

    @Column(name = "class_nickname")
    private String classNickname;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User id;

}