package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.WordRepository;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/07/14
 */
@Service
@RequiredArgsConstructor
public class WordService {

    private WordRepository wordRepository;

    public List<WordResDto> getAllWordAtPage(int page) {
        return wordRepository.findAllByPage(page)
                .stream()
                .map(WordResDto::of)
                .collect(Collectors.toList());
    }

}
