package com.dingdong.lastdance_s.controller;

import com.dingdong.lastdance_s.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleAuthRequest {

    private String token;
    private User user;

}

