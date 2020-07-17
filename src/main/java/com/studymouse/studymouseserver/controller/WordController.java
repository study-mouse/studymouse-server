package com.studymouse.studymouseserver.controller;

import com.studymouse.studymouseserver.service.WordService;
import com.studymouse.studymouseserver.util.ResponseDto;
import com.studymouse.studymouseserver.util.ResponseMessage;
import com.studymouse.studymouseserver.word.Word;
import com.studymouse.studymouseserver.word.dto.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.studymouse.studymouseserver.util.ResponseMessage.*;

/**
 * Created by jyami on 2020/07/14
 */
@RestController
@RequestMapping("api/word")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @GetMapping("{viewType}/page")
    public ResponseEntity<ResponseDto<?>> getAllWord(@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
                                                     @RequestParam(value = "limit", required = false, defaultValue = "40") final int limit,
                                                     @RequestParam("sort") final SortType sortType,
                                                     @PathVariable final ViewType viewType) {
        List<WordResDto> allWordAtPage = wordService.getAllWordAtPage(page, limit, sortType, viewType);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS_WORD_SEARCH, allWordAtPage));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "시작일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "종료일(YYYY-MM-DD)", required = true, dataType = "string", paramType = "query"),
    })
    @GetMapping("{viewType}")
    public ResponseEntity<ResponseDto<?>> getAllFromDate(@RequestParam("startDate") final String startDate,
                                                         @RequestParam("endDate") final String endDate,
                                                         @PathVariable final ViewType viewType) {
        List<WordResDto> allWordBetweenDate = wordService.getAllWordBetweenDate(startDate, endDate, viewType);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS_WORD_SEARCH, allWordBetweenDate));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> saveWord(@RequestBody final WordReqDto wordReqDto) {
        long savedId = wordService.save(wordReqDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.wordIdFormat(SUCCESS_WORD_SAVED, savedId)));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseDto<?>> updateWordColor(@PathVariable final long id, @RequestBody WordUpdateDto wordUpdateDto) {
        long savedId = wordService.updateColor(id, wordUpdateDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.wordIdFormat(SUCCESS_COLOR_CHANGE, savedId)));
    }

    @PatchMapping("archive/{id}")
    public ResponseEntity<ResponseDto<?>> setArchive(@PathVariable final long id) {
        Word word = wordService.setArchive(id);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK,
                ResponseMessage.wordIdFormat(SUCCESS_CHANGE_ARCHIVE_STATE, word.getId()), word.getArchiveTag()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto<?>> deleteWord(@PathVariable final long id) {
        wordService.delete(id);
        return ResponseEntity.ok().body(ResponseDto.of(HttpStatus.OK, SUCCESS_WORD_DELETE));
    }

    @GetMapping("mailTest")
    public @ResponseBody
    ResponseEntity<Map<String, List<WordResDto>>> mailWords() {
        Map<String, List<WordResDto>> allWordBetweenDate = wordService.getAllWordBetweenDate();
        return ResponseEntity.ok().body(allWordBetweenDate);
    }

}
