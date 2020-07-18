package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.MailService;
import com.studymouse.studymouseserver.service.UserService;
import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.word.dto.MailResponseDto;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/07/18
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class MailController {
    private final MailService mailService;
    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * *")
    public @ResponseBody
    ResponseEntity<Void> sendMessage() {
        userService.findAllPushMailsUser()
                .forEach(mailService::sendSimpleMessage);
        return ResponseEntity.ok().build();
    }

    @GetMapping("view")
    public String view(Model model) {
        List<MailResponseDto> mailResponseDtos = new ArrayList<>();
        for (MailDates mailDates : MailDates.values()) {
            List<MailResponseDto.SmallWordCard> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(MailResponseDto.SmallWordCard.builder().english("english").korea("한글").build());
            }
            mailResponseDtos.add(new MailResponseDto(mailDates.getText(), list));
        }
        model.addAttribute("list", mailResponseDtos);
        return "index";
    }
}