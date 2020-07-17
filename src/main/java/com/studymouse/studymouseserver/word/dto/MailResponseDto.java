package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.word.Word;
import lombok.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/07/18
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MailResponseDto {
    private String time;
    private List<SmallWordCard> smallWordCards = Collections.emptyList();

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SmallWordCard {
        private String korea;
        private String english;

        public static SmallWordCard of(Word word){
            return SmallWordCard.builder()
                    .korea(word.getKorean())
                    .english(word.getEnglish())
                    .build();
        }
    }
}
