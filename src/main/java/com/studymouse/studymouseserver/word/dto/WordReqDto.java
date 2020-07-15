package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.word.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@Getter
public class WordReqDto {

    private String english;
    private String korean;
    private String url;
    private String description;
    private String color = "#1a1a1a";

    public Word toEntity() {
        return Word.builder()
                .english(this.english)
                .korean(this.korean)
                .url(this.url)
                .description(this.description)
                .color(this.color)
                .build();
    }

}
