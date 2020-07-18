package com.studymouse.studymouseserver.word;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studymouse.studymouseserver.util.MailDates;
import com.studymouse.studymouseserver.util.TimeUtil;
import com.studymouse.studymouseserver.word.dto.SortType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.studymouse.studymouseserver.word.QWord.word;

/**
 * Created by jyami on 2020/07/14
 */
@RequiredArgsConstructor
public class WordRepositoryImpl implements WordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Word> findAllByPage(int page, int limit, SortType sortType, ArchiveTag archiveTag) {
        int offset = (page - 1) * limit;

        return queryFactory.selectFrom(word)
                .where(word.archiveTag.eq(archiveTag))
                .limit(limit)
                .offset(offset)
                .orderBy(sortType.getOrderSpecifier())
                .fetch();
    }

    @Override
    public List<Word> findAllByDate(LocalDateTime startDate, LocalDateTime finishDate, ArchiveTag archiveTag) {
        return queryFactory.selectFrom(word)
                .where(word.archiveTag.eq(archiveTag))
                .where(word.createdDate.between(startDate, finishDate))
                .fetch();
    }

    @Override
    public List<Word> findAllMailWords(LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.selectFrom(word)
                .where(word.archiveTag.eq(ArchiveTag.NOT_ARCHIVE))
                .where(word.createdDate.between(startDate, endDate))
                .limit(4)
                .fetch();
    }
}

