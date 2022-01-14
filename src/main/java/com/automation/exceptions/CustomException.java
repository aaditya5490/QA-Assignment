package com.automation.exceptions;

import org.slf4j.helpers.MessageFormatter;


public class CustomException extends RuntimeException {

    private final CustomExceptionType customExceptionType;

    public CustomException(CustomExceptionType customExceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
        this.customExceptionType = customExceptionType;
    }

    public CustomException(Throwable throwable, CustomExceptionType customExceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage(), throwable);
        this.customExceptionType = customExceptionType;
    }

    public CustomException(CustomExceptionType customExceptionType, Throwable throwable) {
        super(throwable);
        this.customExceptionType = customExceptionType;
    }

    public CustomExceptionType getCustomExceptionType() {
        return customExceptionType;
    }
}
