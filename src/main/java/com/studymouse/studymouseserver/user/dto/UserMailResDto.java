package com.studymouse.studymouseserver.user.dto;

import com.studymouse.studymouseserver.user.User;
import lombok.*;

/**
 * Created by jyami on 2020/07/18
 */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserMailResDto {
    private long id;
    private String email;

    public static UserMailResDto of(User user){
        return new UserMailResDto(user.getId(), user.getEmail());
    }
}
