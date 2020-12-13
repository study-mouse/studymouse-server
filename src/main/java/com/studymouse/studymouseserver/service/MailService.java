package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.user.dto.UserMailResDto;
import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by jyami on 2020/07/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final WordService wordService;

    public void sendSimpleMessage(UserMailResDto resDto) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(resDto.getEmail());
            helper.setSubject("[스터디마우스] 오늘의 단어장 : " + LocalDate.now().toString());

            Context context = new Context();
            context.setVariable("list", wordService.getAllWordMailDate(resDto.getId()));
            String html = templateEngine.process("index", context);

            helper.setText(html, true);
            emailSender.send(message);
            log.info(resDto.toString() + " message send success");
        } catch (MessagingException e) {
            log.error(resDto.toString());
        }
    }

}
