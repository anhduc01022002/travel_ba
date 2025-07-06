package com.travelify.travelify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1002, "User not found with this email", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1003, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_VALIDATE(1004, "Email must be a valid Gmail address", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1005, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_OTP(1009, "Your OTP is invalid", HttpStatus.BAD_REQUEST),

    HOTEL_NOT_FOUND(2001, "Hotel not found", HttpStatus.NOT_FOUND),
    HOTEL_NAME_REQUIRED(2002, "Hotel name is required", HttpStatus.BAD_REQUEST),
    HOTEL_RATE_REQUIRED(2003, "Hotel rate is required", HttpStatus.BAD_REQUEST),
    HOTEL_RATE_INVALID(2004, "Hotel rate must be positive", HttpStatus.BAD_REQUEST),
    HOTEL_PRICE_REQUIRED(2005, "Hotel price is required", HttpStatus.BAD_REQUEST),
    HOTEL_PRICE_INVALID(2006, "Hotel price must be positive", HttpStatus.BAD_REQUEST),
    HOTEL_ADDRESS_REQUIRED(2007, "Hotel address is required", HttpStatus.BAD_REQUEST)

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
