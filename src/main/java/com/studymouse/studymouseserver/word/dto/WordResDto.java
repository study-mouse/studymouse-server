package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.word.Word;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WordResDto {

    private String english;
    private String korean;
    private String url;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static WordResDto of(Word word) {
        return WordResDto.builder()
                .english(word.getEnglish())
                .korean(word.getKorean())
                .url(word.getUrl())
                .createdDate(word.getCreatedDate())
                .modifiedDate(word.getModifiedDate())
                .build();
    }

}
