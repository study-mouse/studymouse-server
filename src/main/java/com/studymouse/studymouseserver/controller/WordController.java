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
@RequestMapping("word")
@RequiredArgsConstructor
public class WordController {

    private WordService wordService;

    @GetMapping("")
    public ResponseEntity<List<WordResDto>> getAllWord(@RequestParam("page") final int page) {
        return ResponseEntity.ok().body(wordService.getAllWordAtPage(page));
    }

    @PostMapping("")
    public ResponseEntity<Void> saveWord(@RequestBody WordReqDto wordReqDto){
        return ResponseEntity.ok().body(null);
    }





}
