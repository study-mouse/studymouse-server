package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.MailService;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

/**
 * Created by jyami on 2020/07/18
 */
@RequestMapping("api/mail") // 후에 mail로 바꾸기
@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("send")
    public @ResponseBody
    ResponseEntity<Void> sendMessage() throws MessagingException {
        mailService.sendSimpleMessage("mor2222@naver.com");
        return ResponseEntity.ok().build();
    }

    @GetMapping("mailTest")
    public @ResponseBody
    ResponseEntity mailWords() {
        Map<String, List<WordResDto>> allWordBetweenDate = mailService.getAllWordBetweenDate();
        return ResponseEntity.ok().body(allWordBetweenDate);
    }

    @GetMapping("view")
    public String view() {
        return "index.html";
    }
}