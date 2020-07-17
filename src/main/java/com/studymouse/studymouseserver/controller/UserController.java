package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.security.store.AccessUserManager;
import com.studymouse.studymouseserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AccessUserManager accessUserManager;

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
