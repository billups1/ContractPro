package com.hs.ContractPro.web.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;  // 1 성공, -1 실패
    private String message;
    private T data;

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
