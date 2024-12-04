package com.team5.on_stage.global.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // GLOBAL
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "GLOBAL-01","올바르지 않은 값"),

    // USER
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT, "USER-01", "중복된 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-02", "유저를 찾을 수 없습니다."),
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "USER-03", "변경 사항이 없습니다."),

    // Link
    LINK_NOT_FOUND(HttpStatus.NOT_FOUND, "LINK-01","link not found"),
    LINK_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "LINK-02","link detail not found"),
    SOCIAL_LINK_NOT_FOUND(HttpStatus.NOT_FOUND, "LINK-03","social link not found"),
    THEME_NOT_FOUND(HttpStatus.NOT_FOUND, "LINK-04","theme not found"),

    // Subscribe
    SUBSCRIBE_NOT_FOUND(HttpStatus.NOT_FOUND, "SUBSCRIBE-01", "subscribe not found"),;

    // ANALYSIS


    // CONCERT


    // ARTICLE



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
