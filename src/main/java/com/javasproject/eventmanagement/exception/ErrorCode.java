package com.javasproject.eventmanagement.exception;


public enum ErrorCode {
    USER_EXISTED(1001, "User is existed"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    USERNAME_INVALID_EXCEPTION(102, "Username is invalid"),
    PASSWORD_INVALID_EXCEPTION(103, "Password is invalid"),
    USER_NOT_EXIST(104, "User not exist"),
    ROLE_NOT_EXIST(114, "Role not exist"),
    INVALID_CREDENTIALS(105, "Wrong credentials"),
    ROLE_EXISTED(106, "Role is existed"),
    PHONE_EXISTED(107, "Phone is existed"),
    EMAIL_EXISTED(108, "Email is existed"),
    NEWS_NOT_FOUND(109, "News is not existed"),
    RESOURCE_NOT_FOUND(1010, "News is not existed"),
    NOTIFICATION_NOT_FOUND(1011, "News is not existed"),
    REQUEST_NOT_FOUND(1012, "News is not existed"),
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
