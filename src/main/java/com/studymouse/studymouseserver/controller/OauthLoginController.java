package com.studymouse.studymouseserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by jyami on 2020/07/18
 */
@Controller
public class OauthLoginController {
    @PostMapping("login")
    public RedirectView googleLogin(){
        return new RedirectView("oauth2/authorization/google");
    }

    @PostMapping("logout")
    public RedirectView googleLogout(){
        return new RedirectView("/api/user/logout");
    }
}
