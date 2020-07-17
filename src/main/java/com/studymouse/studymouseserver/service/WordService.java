package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.util.TimeUtil;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/07/14
 */
@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public List<WordResDto> getAllWordAtPage(int page, int limit, SortType sortType, ViewType viewType) {
        return wordRepository.findAllByPage(page, limit, sortType, viewType.getArchiveTag())
                .stream()
                .map(WordResDto::of)
                .collect(Collectors.toList());
    }

    public List<WordResDto> getAllWordBetweenDate(String startDate, String endDate, ViewType viewType) {

        return wordRepository.findAllByDate(TimeUtil.getStartDate(startDate), TimeUtil.getEndDate(endDate), viewType.getArchiveTag())
                .stream()
                .map(WordResDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(WordReqDto wordReqDto) {
        wordRepository.save(wordReqDto.toEntity());
    }

    @Transactional
    public void updateColor(long id, WordUpdateDto wordUpdateDto) {
        Word word = getWordFromId(id);
        word.setColor(wordUpdateDto.getColor());
        wordRepository.save(word);
    }

    @Transactional
    public void setArchive(long id) {
        Word word = getWordFromId(id);
        word.setArchiveTag(word.getArchiveTag().getOppositionArchive());
        wordRepository.save(word);
    }

    @Transactional
    public void delete(long id) {
        Word word = getWordFromId(id);
        wordRepository.delete(word);
    }

    private Word getWordFromId(long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 ID 값입니다."));
    }

}
