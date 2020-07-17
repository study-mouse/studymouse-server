package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.user.Type;
import lombok.RequiredArgsConstructor;
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
}
