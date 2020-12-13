package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.MailService;
import com.studymouse.studymouseserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/07/18
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class MailScheduler {
    private final MailService mailService;
    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * *")
    public void sendMessage() {
        userService.findAllPushMailsUser()
                .forEach(mailService::sendSimpleMessage);
    }

}