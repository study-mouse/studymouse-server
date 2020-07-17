package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.word.Word;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WordResDto {

    private Long id;
    private String english;
    private String korean;
    private List<Description> description;
    private String url;
    private String color;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static WordResDto of(Word word, List<Description> description) {
        return WordResDto.builder()
                .id(word.getId())
                .english(word.getEnglish())
                .korean(word.getKorean())
                .url(word.getUrl())
                .color(word.getColor())
                .description(description)
                .createdDate(word.getCreatedDate())
                .modifiedDate(word.getModifiedDate())
                .build();
    }

}
