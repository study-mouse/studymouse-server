package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.util.TimeUtil;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.WordReqDto;
import com.studymouse.studymouseserver.word.dto.WordResDto;
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

    public List<WordResDto> getAllWordAtPage(int page) {
        return wordRepository.findAllByPage(page)
                .stream()
                .map(WordResDto::of)
                .collect(Collectors.toList());
    }

    public List<WordResDto> getAllWordBetweenDate(String startDate, String endDate) {

        return wordRepository.findAllByDate(TimeUtil.getStartDate(startDate), TimeUtil.getEndDate(endDate))
                .stream()
                .map(WordResDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(WordReqDto wordReqDto) {
        wordRepository.save(wordReqDto.toEntity());
    }

    @Transactional
    public void delete(Long id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 ID 값입니다."));
        wordRepository.delete(word);
    }

}
