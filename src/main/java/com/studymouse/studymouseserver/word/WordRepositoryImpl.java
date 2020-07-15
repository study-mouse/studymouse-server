package com.studymouse.studymouseserver.word;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public List<Word> findAllByPage(int page, int limit, SortType sortType) {
        int offset = (page - 1) * limit;

        return queryFactory.selectFrom(word)
                .limit(limit)
                .orderBy(sortType.getOrderMethod())
                .offset(offset).fetch();
    }

    @Override
    public List<Word> findAllByDate(LocalDateTime startDate, LocalDateTime finishDate) {

        return queryFactory.selectFrom(word)
                .where(word.modifiedDate.between(startDate, finishDate))
                .fetch();
    }
}

