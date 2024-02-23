package com.hs.ContractPro.common.exception;

import com.hs.ContractPro.web.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handle(CustomException ex) {
        return new ResponseEntity(new ApiResponse<>(-1, "에러 발생"), HttpStatus.BAD_REQUEST);
    }

}
