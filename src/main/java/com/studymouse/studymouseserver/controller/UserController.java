package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.LoginUser;
import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
import com.studymouse.studymouseserver.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.studymouse.studymouseserver.util.ResponseMessage.SUCCESS_USER_DATE;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("push")
    public ResponseEntity<?> togglePushMail(@LoginUser AccessUser accessUser) {
        boolean toggleResult = userService.togglePushMail(accessUser.getEmail());
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_USER_DATE, toggleResult));
    }

    @GetMapping("")
    public ResponseEntity<?> userMain(@LoginUser AccessUser accessUser) {
        UserInfoResDto userInfoResDto = new UserInfoResDto(userService.getNowAccessUser(accessUser));
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_USER_DATE, userInfoResDto));
    }
}
