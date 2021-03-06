package com.studymouse.studymouseserver.word;

import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.util.TimeUtil;
import com.studymouse.studymouseserver.word.dto.SortType;
import com.studymouse.studymouseserver.word.dto.ViewType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@Repository
public interface WordRepositoryCustom {

    List<Word> findAllByPage(User user, int page, int limit, SortType sortType, ArchiveTag archiveTag);

    List<Word> findAllByDate(User user, LocalDateTime startDate, LocalDateTime finishDate, ArchiveTag archiveTag);

    List<Word> findAllMailWords(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}
