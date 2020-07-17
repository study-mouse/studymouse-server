package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.WordService;
import com.studymouse.studymouseserver.word.dto.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@RestController
@RequestMapping("api/word")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping("{viewType}/page")
    public ResponseEntity<List<WordResDto>> getAllWord(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                                       @RequestParam(value = "limit", required = false, defaultValue = "40") final int limit,
                                                       @RequestParam("sort") final SortType sortType,
                                                       @PathVariable final ViewType viewType) {
        return ResponseEntity.ok().body(wordService.getAllWordAtPage(page, limit, sortType, viewType));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "시작일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "종료일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping("{viewType}")
    public ResponseEntity<List<WordResDto>> getAllFromDate(@RequestParam("startDate") final String startDate,
                                                           @RequestParam("endDate") final String endDate,
                                                           @PathVariable final ViewType viewType) {
        return ResponseEntity.ok().body(wordService.getAllWordBetweenDate(startDate, endDate, viewType));
    }

    @PostMapping("")
    public ResponseEntity<Void> saveWord(@RequestBody final WordReqDto wordReqDto) {
        wordService.save(wordReqDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateWordColor(@PathVariable final long id, @RequestBody WordUpdateDto wordUpdateDto) {
        wordService.updateColor(id, wordUpdateDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("archive/{id}")
    public ResponseEntity<Void> setArchive(@PathVariable final long id){
        wordService.setArchive(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable final long id) {
        wordService.delete(id);
        return ResponseEntity.ok().build();
    }


}
