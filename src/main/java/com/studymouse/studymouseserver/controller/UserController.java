package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.security.store.AccessUserManager;
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
    private final AccessUserManager accessUserManager;

    @PostMapping("login/google")
    public String loginGoogle() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("logout")
    public @ResponseBody
    RedirectView logout() {
        AccessUser accessUser = accessUserManager.getAccessUser();

        if (accessUser.getType() == Type.SOCIAL) {
            return new RedirectView("sociallogout");
        }

        httpSession.removeAttribute("user");
        return new RedirectView("");
    }

    @GetMapping("push")
    public @ResponseBody
    ResponseEntity<Boolean> togglePushMail() {
        AccessUser accessUser = accessUserManager.getAccessUser();

        boolean toggleResult = userService.togglePushMail(accessUser.getEmail());

        return ResponseEntity.ok(toggleResult);
    }

    @GetMapping("")
    public @ResponseBody
    ResponseEntity<AccessUser> userMain() {
        AccessUser accessUser = accessUserManager.getAccessUser();

        return ResponseEntity.ok(accessUser);
    }
}
