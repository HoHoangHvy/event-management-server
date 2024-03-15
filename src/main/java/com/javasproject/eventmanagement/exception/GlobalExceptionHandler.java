package com.javasproject.eventmanagement.exception;

import com.javasproject.eventmanagement.dto.request.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespone> handleRuntimeException(RuntimeException e){
        ApiRespone respone = new ApiRespone();
        respone.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        respone.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(respone);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ApiRespone respone = new ApiRespone();
        String errorKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(errorKey);
        respone.setCode(errorCode.getCode());
        respone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(respone);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handleAppException(AppException e){
        ApiRespone respone = new ApiRespone();
        respone.setCode(e.getErrorCode().getCode());
        respone.setMessage(e.getErrorCode().getMessage());
        return ResponseEntity.badRequest().body(respone);
    }
}
