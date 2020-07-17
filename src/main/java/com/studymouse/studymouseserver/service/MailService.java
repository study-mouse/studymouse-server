package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
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
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final WordService wordService;

    public void sendSimpleMessage(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("[스터디마우스] 오늘의 단어장 - 2020/07/18");

        Context context = new Context();
        context.setVariables(Collections.unmodifiableMap(wordService.getAllWordBetweenDate()));
        String html = templateEngine.process("index", context);

        helper.setText(html, true);
        emailSender.send(message);
    }

}
