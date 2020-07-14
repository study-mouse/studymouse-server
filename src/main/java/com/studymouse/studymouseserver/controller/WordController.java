package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.WordService;
import com.studymouse.studymouseserver.word.dto.WordReqDto;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@RestController
@RequestMapping("api/word")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping("page")
    public ResponseEntity<List<WordResDto>> getAllWord(@RequestParam("page") final int page) {
        return ResponseEntity.ok().body(wordService.getAllWordAtPage(page));
    }

    @GetMapping("")
    public ResponseEntity<List<WordResDto>> getAllFromDate(@RequestParam("startDate") final String startDate,
                                                           @RequestParam("endDate") final String endDate) {
        return ResponseEntity.ok().body(wordService.getAllWordBetweenDate(startDate, endDate));
    }

    @PostMapping("")
    public ResponseEntity<Void> saveWord(@RequestBody WordReqDto wordReqDto) {
        wordService.save(wordReqDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable final long id) {
        wordService.delete(id);
        return ResponseEntity.ok().build();
    }


}
