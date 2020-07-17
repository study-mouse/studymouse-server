package com.studymouse.studymouseserver.mail;

import com.studymouse.studymouseserver.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.mail.MessagingException;

/**
 * Created by jyami on 2020/07/17
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void mailSend() throws MessagingException {
        mailService.sendSimpleMessage("mor2222@naver.com");
    }

}