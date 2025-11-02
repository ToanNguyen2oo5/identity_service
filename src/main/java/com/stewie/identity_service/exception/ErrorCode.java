package com.stewie.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(999,"user existed", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(101,"user existed", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(102,"username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(103,"password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    INVALID_KEY(104,"invalid message key", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(105,"user not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(106,"unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(107,"you do not have permission",HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
