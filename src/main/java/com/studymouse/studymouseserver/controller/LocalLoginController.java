package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
import com.studymouse.studymouseserver.user.dto.UserReqDto;
import com.studymouse.studymouseserver.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/07/19
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/mouse")
public class LocalLoginController {

    private final UserService userService;
    // 192.168.219.111:8080
    @PostMapping("login")
    public ResponseEntity<ResponseDto> loginWithGoogle(@RequestBody UserReqDto userReqDto){
        log.info("login");
        UserInfoResDto save = userService.login(userReqDto);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "로그인 성공", save));
    }

    @PostMapping("logout")
    public ResponseEntity<ResponseDto> loginWithGoogle(){
        userService.logout();
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, "로그아웃 완료"));
    }
}
