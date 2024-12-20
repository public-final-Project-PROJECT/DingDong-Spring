package com.dingdong.lastdance_s.controller;

import lombok.Getter;

@Getter
public class GoogleUser
{
    private String email;

    private String name;

    private String picture;

    public GoogleUser(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
