package com.studymouse.studymouseserver.word;

import com.studymouse.studymouseserver.word.dto.SortType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@Repository
public interface WordRepositoryCustom {

    List<Word> findAllByPage(int page, int limit, SortType sortType);

    List<Word> findAllByDate(LocalDateTime startDate, LocalDateTime finishDate);

}
