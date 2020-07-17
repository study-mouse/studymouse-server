package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

/**
 * Created by jyami on 2020/07/18
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @Scheduled(cron = "0 0 9 * * *")
    public @ResponseBody ResponseEntity<Void> sendMessage() throws MessagingException {
        mailService.sendSimpleMessage("mor2222@naver.com");
        log.info("cron job exe");
        return ResponseEntity.ok().build();
    }

    @GetMapping("view")
    public String view() {
        return "index.html";
    }
}