package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.WordService;
import com.studymouse.studymouseserver.word.dto.SortType;
import com.studymouse.studymouseserver.word.dto.WordReqDto;
import com.studymouse.studymouseserver.word.dto.WordResDto;
import com.studymouse.studymouseserver.word.dto.WordUpdateDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    public ResponseEntity<List<WordResDto>> getAllWord(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                                       @RequestParam(value = "limit", required = false, defaultValue = "40") final int limit,
                                                       @RequestParam("sort") final SortType sortType) {
        return ResponseEntity.ok().body(wordService.getAllWordAtPage(page, limit, sortType));
    }

    @GetMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "시작일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "종료일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
    })
    public ResponseEntity<List<WordResDto>> getAllFromDate(@RequestParam("startDate") final String startDate,
                                                           @RequestParam("endDate") final String endDate) {
        return ResponseEntity.ok().body(wordService.getAllWordBetweenDate(startDate, endDate));
    }

    @PostMapping("")
    public ResponseEntity<Void> saveWord(@RequestBody final WordReqDto wordReqDto) {
        wordService.save(wordReqDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateWord(@PathVariable final long id, @RequestBody WordUpdateDto wordUpdateDto) {
        wordService.update(id, wordUpdateDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable final long id) {
        wordService.delete(id);
        return ResponseEntity.ok().build();
    }


}
