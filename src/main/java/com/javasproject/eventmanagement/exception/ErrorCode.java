package com.javasproject.eventmanagement.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User is existed"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    USERNAME_INVALID_EXCEPTION(102, "Username is invalid"),
    PASSWORD_INVALID_EXCEPTION(103, "Password is invalid"),
    USER_NOT_EXIST(104, "User not exist"),
    INVALID_CREDENTIALS(105, "Wrong credentials"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
