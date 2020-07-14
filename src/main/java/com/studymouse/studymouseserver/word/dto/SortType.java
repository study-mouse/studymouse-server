package com.studymouse.studymouseserver.word.dto;

import com.querydsl.core.types.OrderSpecifier;

import static com.studymouse.studymouseserver.word.QWord.word;

/**
 * Created by jyami on 2020/07/15
 */
public enum SortType {
    WORD_ENG_ASC(word.english.asc()), WORD_ENG_DESC(word.english.desc()),
    COLOR_ASC(word.color.asc()),COLOR_DESC(word.color.desc());

    private OrderSpecifier<String> orderMethod;

    SortType(OrderSpecifier<String> orderMethod) {
        this.orderMethod = orderMethod;
    }

    public OrderSpecifier<String> getOrderMethod() {
        return orderMethod;
    }
}
