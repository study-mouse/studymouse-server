package com.studymouse.studymouseserver.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/07/18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseMessage {
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String SUCCESS_WORD_SEARCH = "단어 리스트 조회 성공";
    public static final String SUCCESS_WORD_SAVED = "새로운 단어 저장 성공";
    public static final String SUCCESS_COLOR_CHANGE = "색 변경 성공";
    public static final String SUCCESS_CHANGE_ARCHIVE_STATE = "아카이브 상태 변경 성공";
    public static final String SUCCESS_WORD_DELETE = "단어 삭제 성공";
    public static final String SUCCESS_USER_DATE = "유저 정보 조회 성공";
    public static final String SUCCESS_USER_MAIL_STATE = "유저 푸시메일 상태 변경 성공";

    public static String wordIdFormat(String message, long id) {
        return String.format("Word ID : %s, %s", id, message);
    }
}
