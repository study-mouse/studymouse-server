package com.studymouse.studymouseserver.word;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@Repository
public interface WordRepositoryCustom {

    List<Word> findAllByPage(int page);

}
