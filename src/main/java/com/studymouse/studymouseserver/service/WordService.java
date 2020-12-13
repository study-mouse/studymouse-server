package com.studymouse.studymouseserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymouse.studymouseserver.exception.NotAccessUserException;
import com.studymouse.studymouseserver.exception.ParsingException;
//import com.studymouse.studymouseserver.security.LoginUser;
import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.security.store.AccessUserManager;
import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.util.TimeUtil;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.AccessControlContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/07/14
 */
@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final AccessUserManager manager;

    public List<WordResDto> getAllWordAtPage(int page, int limit, SortType sortType, ViewType viewType) {
//        User nowAcessUser = userService.getNowAccessUser(accessUser);
        User nowAcessUser =userService.getNowAccessUser(manager.getAccessUser());
        return wordRepository.findAllByPage(nowAcessUser, page, limit, sortType, viewType.getArchiveTag())
                .stream()
                .map(this::writeWordResDto)
                .collect(Collectors.toList());
    }

    public List<WordResDto> getAllWordBetweenDate(AccessUser accessUser, String startDate, String endDate, ViewType viewType) {
        User nowAcessUser = userService.getNowAccessUser(accessUser);
        return wordRepository.findAllByDate(nowAcessUser, TimeUtil.getStartDate(startDate), TimeUtil.getEndDate(endDate), viewType.getArchiveTag())
                .stream()
                .map(this::writeWordResDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MailResponseDto> getAllWordMailDate(Long userId) {
        LocalDate now = LocalDate.now();
        List<MailResponseDto> mailResponseDtos = new ArrayList<>();
        for (MailDates mailDates : MailDates.values()) {
            List<Word> allMailWords = wordRepository.findAllMailWords(userId, mailDates.getStartTime(now), mailDates.getEndTime(now));
            List<MailResponseDto.SmallWordCard> collect =
                    allMailWords
                            .stream()
                            .map(MailResponseDto.SmallWordCard::of)
                            .collect(Collectors.toList());
            mailResponseDtos.add(new MailResponseDto(mailDates.getText(), collect));
        }
        return mailResponseDtos;
    }

    private WordResDto writeWordResDto(Word m) {
        try {
            List<Description> descriptions = objectMapper.readValue(m.getDescription(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Description.class));
            return WordResDto.of(m, descriptions);
        } catch (JsonProcessingException e) {
            throw new ParsingException(e.getMessage(), e);
        }
    }

    @Transactional
    public long save(WordReqDto wordReqDto, AccessUser accessUser) {
        try {
            User nowAcessUser = userService.getNowAccessUser(accessUser);
            String descriptionJsonString = objectMapper.writeValueAsString(wordReqDto.getDescription());
            return wordRepository.save(wordReqDto.toEntity(descriptionJsonString, nowAcessUser)).getId();
        } catch (JsonProcessingException e) {
            throw new ParsingException(e.getMessage(), e);
        }
    }

    @Transactional
    public long updateColor(long id, WordUpdateDto wordUpdateDto, AccessUser accessUser) {
        Word word = getWordFromId(id);
        checkUserAuthority(word, userService.getNowAccessUser(accessUser));
        word.setColor(wordUpdateDto.getColor());
        return wordRepository.save(word).getId();
    }

    @Transactional
    public Word setArchive(long id, AccessUser accessUser) {
        Word word = getWordFromId(id);
        checkUserAuthority(word, userService.getNowAccessUser(accessUser));
        word.setArchiveTag(word.getArchiveTag().getOppositionArchive());
        return wordRepository.save(word);
    }

    @Transactional
    public void delete(long id, AccessUser accessUser) {
        Word word = getWordFromId(id);
        checkUserAuthority(word, userService.getNowAccessUser(accessUser));
        wordRepository.delete(word);
    }

    private void checkUserAuthority(Word word, User user) {
        if (!word.getUser().getId().equals(user.getId())) {
            throw new NotAccessUserException("해당유저가 접근할 수 없는 단어입니다");
        }
    }

    private Word getWordFromId(long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 ID 값입니다."));
    }


}
