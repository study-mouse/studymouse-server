package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.CustomOAuthUserService;
import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.dto.UserLoginReqDto;
import com.studymouse.studymouseserver.user.dto.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("")
    public ResponseEntity<Void> saveUser(@RequestBody final UserReqDto userReqDto) {
        userService.save(userReqDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody final UserLoginReqDto userLoginReqDto) {
        AccessUser accessUser = userService.login(userLoginReqDto);

        httpSession.setAttribute("user", accessUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("logout")
    public ResponseEntity<Void> logout() {
        httpSession.removeAttribute("user");

        return ResponseEntity.ok().build();
    }
}
