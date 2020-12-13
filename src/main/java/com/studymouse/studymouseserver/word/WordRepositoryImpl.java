package com.studymouse.studymouseserver.word;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.word.dto.SortType;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.studymouse.studymouseserver.word.QWord.word;
import static com.studymouse.studymouseserver.user.QUser.user;

/**
 * Created by jyami on 2020/07/14
 */
@RequiredArgsConstructor
public class WordRepositoryImpl implements WordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Word> findAllByPage(User accessUser, int page, int limit, SortType sortType, ArchiveTag archiveTag) {
        int offset = (page - 1) * limit;

        return queryFactory.selectFrom(word)
                .where(user.id.eq(accessUser.getId()))
                .where(word.archiveTag.eq(archiveTag))
                .limit(limit)
                .offset(offset)
                .orderBy(sortType.getOrderSpecifier())
                .fetch();
    }

    @Override
    public List<Word> findAllByDate(User accessUser, LocalDateTime startDate, LocalDateTime finishDate, ArchiveTag archiveTag) {
        return queryFactory.selectFrom(word)
                .where(user.id.eq(accessUser.getId()))
                .where(word.archiveTag.eq(archiveTag))
                .where(word.createdDate.between(startDate, finishDate))
                .fetch();
    }

    @Override
    public List<Word> findAllMailWords(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return queryFactory.selectFrom(word)
                .where(user.id.eq(userId))
                .where(word.archiveTag.eq(ArchiveTag.NOT_ARCHIVE))
                .where(word.createdDate.between(startDate, endDate))
                .limit(4)
                .fetch();
    }
}

