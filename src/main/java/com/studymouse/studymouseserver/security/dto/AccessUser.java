package com.studymouse.studymouseserver.security.dto;

import com.studymouse.studymouseserver.user.User;
import lombok.Getter;

@Getter
public class AccessUser {

    private String name;
    private String email;
    private String picture;

    public AccessUser(final User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
