package com.studymouse.studymouseserver.word;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/07/14
 */
public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryCustom {
}
