package com.studymouse.studymouseserver.user.dto;

import com.studymouse.studymouseserver.user.Role;
import com.studymouse.studymouseserver.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserReqDto {
    private String name;
    private String email;
    private String picture;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.USER)
                .build();
    }
}
