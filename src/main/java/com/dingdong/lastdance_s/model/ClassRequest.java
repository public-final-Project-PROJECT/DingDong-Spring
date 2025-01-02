package com.dingdong.lastdance_s.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ClassRequest
{
    private String email;
    private String schoolName;
    private int grade;
    private int classNo;
    private String classNickname;
    private Timestamp createTime;
    private Timestamp expireTime;
}
