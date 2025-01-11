package com.dingdong.lastdance_s.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Column(name = "class_created")
    private Timestamp classCreated;

    @Column(name = "class_expire")
    private Timestamp classExpire;

    @PrePersist
    public void setClassExpire()
    {
        if (classCreated == null)
        {
            classCreated = Timestamp.valueOf(LocalDateTime.now());
        }
        LocalDateTime createdDate = classCreated.toLocalDateTime();
        int expireYear = createdDate.getYear() + 2;
        LocalDateTime expireDate = LocalDateTime.of(expireYear, 3, 1, 0, 0);
        this.classExpire = Timestamp.valueOf(expireDate);
    }
}
