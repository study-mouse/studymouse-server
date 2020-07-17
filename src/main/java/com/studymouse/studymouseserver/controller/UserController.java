package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.CustomOAuthUserService;
import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.Type;
import com.studymouse.studymouseserver.user.dto.UserLoginReqDto;
import com.studymouse.studymouseserver.user.dto.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

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
    public RedirectView logout() {
        AccessUser accessUser = (AccessUser) httpSession.getAttribute("user");

        if (Objects.isNull(accessUser)) {
            throw new IllegalArgumentException("로그인 정보 X");
        }

        if (accessUser.getType() == Type.SOCIAL) {
            return new RedirectView("sociallogout");
        }

        httpSession.removeAttribute("user");
        return new RedirectView("");
    }
}
