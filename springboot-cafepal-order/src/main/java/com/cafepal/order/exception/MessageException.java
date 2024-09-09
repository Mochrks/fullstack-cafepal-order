package com.cafepal.order.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MessageException extends RuntimeException {
    private String message;
    private String details;
    private String suggestion;
    private HttpStatus httpStatus;
    private String errorCode;

    public MessageException(String message, String details, String suggestion, String errorCode,
            HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.details = details;
        this.suggestion = suggestion;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    // getters and setters
}