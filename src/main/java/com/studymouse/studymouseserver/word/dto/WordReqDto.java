package com.studymouse.studymouseserver.word.dto;

import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.word.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@Getter
public class WordReqDto {

    private String english;
    private String korean;
    private String url;
    private List<Description> description;

    public Word toEntity(String descriptionJsonString, User user) {
        return Word.builder()
                .english(this.english)
                .korean(this.korean)
                .url(this.url)
                .description(descriptionJsonString)
                .user(user)
                .build();
    }



}
