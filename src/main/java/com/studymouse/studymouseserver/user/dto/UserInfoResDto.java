package com.studymouse.studymouseserver.user.dto;

import com.studymouse.studymouseserver.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResDto {
    private String name;
    private String email;
    private String picture;
    private Boolean isEnablePushMail;

    public UserInfoResDto(final User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.isEnablePushMail = user.isPushMail();
    }
}
