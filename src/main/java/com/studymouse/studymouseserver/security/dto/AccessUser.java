package com.studymouse.studymouseserver.security.dto;

import com.studymouse.studymouseserver.user.Type;
import com.studymouse.studymouseserver.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AccessUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    private Type type;

    public AccessUser(final User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.type = user.getType();
    }
}
