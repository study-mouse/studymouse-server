package com.studymouse.studymouseserver.word;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/14
 */

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@EnableJpaAuditing
@ExtendWith(SpringExtension.class)
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    void createTest() {
        Word wordBefore = new Word(null, "English", "한글", "http://www.naver.com");
        Word save = wordRepository.save(wordBefore);

        Word word = wordRepository.findAll().get(0);
        assertAll(() -> {
            assertThat(word.getKorean()).isEqualTo("한글");
            assertThat(word.getEnglish()).isEqualTo("English");
            assertThat(word.getUrl()).isEqualTo("http://www.naver.com");
        });
        System.out.println(word);

    }

    @Test
    void deleteTest() {
        Word wordBefore = new Word(null, "English", "한글", "http://www.naver.com");
        Word save = wordRepository.save(wordBefore);

        Word word = wordRepository.findAll().get(0);
        wordRepository.delete(word);
        assertThat(wordRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void updateTest(){
        Word wordBefore = new Word(null, "English", "한글", "http://www.naver.com");
        Word save = wordRepository.save(wordBefore);

        Word word = wordRepository.findAll().get(0);
        word.setEnglish("EnglishWord");
        wordRepository.save(word);

        Word wordSave = wordRepository.findAll().get(0);
        assertAll(() -> {
            assertThat(wordSave.getEnglish()).isEqualTo("EnglishWord");
        });
        System.out.println(wordSave);
    }

    @Test
    void findAllByPageTest(){

        Word wordBefore = new Word(null, "English", "한글", "http://www.naver.com");
        Word save = wordRepository.save(wordBefore);

        List<Word> allByPage = wordRepository.findAllByPage(1);
        System.out.println(allByPage);
    }

}