package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
import com.studymouse.studymouseserver.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.studymouse.studymouseserver.util.ResponseMessage.SUCCESS_USER_DATE;
import static com.studymouse.studymouseserver.util.ResponseMessage.SUCCESS_USER_MAIL_STATE;

@RestController
@Slf4j
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("push")
    public ResponseEntity<?> togglePushMail() {
//        log.info(accessUser.toString());
        boolean toggleResult = userService.togglePushMail("");
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_USER_MAIL_STATE, toggleResult));
    }

    @GetMapping("")
    public ResponseEntity<?> userMain() {
        UserInfoResDto userInfoResDto = new UserInfoResDto(userService.getNowAccessUser());
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_USER_DATE, userInfoResDto));
    }
}
