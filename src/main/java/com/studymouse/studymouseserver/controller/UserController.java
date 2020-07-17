package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.user.Type;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("login/google")
    public String loginGoogle() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("logout")
    public @ResponseBody
    RedirectView logout() {
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

    @GetMapping("push")
    public @ResponseBody
    ResponseEntity<Boolean> togglePushMail() {
        AccessUser accessUser = (AccessUser) httpSession.getAttribute("user");

        if (Objects.isNull(accessUser)) {
            throw new IllegalArgumentException("로그인 정보 X");
        }

        boolean toggleResult = userService.togglePushMail(accessUser.getEmail());

        return ResponseEntity.ok(toggleResult);
    }
}
