package com.hs.ContractPro.web;

import com.hs.ContractPro.domain.auth.dto.TokenRequest;
import com.hs.ContractPro.domain.user.dto.SignUpView;
import com.hs.ContractPro.service.AuthService;
import com.hs.ContractPro.web.auth.dto.LoginRequest;
import com.hs.ContractPro.web.auth.dto.TokenDto;
import com.hs.ContractPro.web.common.dto.ApiResponse;
import com.hs.ContractPro.web.auth.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/user")
    public ResponseEntity create(@RequestBody @Valid UserCreateRequest request) {
        SignUpView view = authService.create(request);
        return new ResponseEntity(new ApiResponse<>(1, "유저 생성 성공", view), HttpStatus.CREATED);
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest request) {
        TokenDto tokenDto = authService.login(request);
        return new ResponseEntity(new ApiResponse<>(1, "로그인 성공", tokenDto), HttpStatus.CREATED);
    }

    @PostMapping("/api/reissue")
    public ResponseEntity reissue(@RequestBody TokenRequest request) {
        TokenDto tokenDto = authService.reissue(request);
        return new ResponseEntity(new ApiResponse<>(1, "Access 토큰 재발행 성공", tokenDto), HttpStatus.CREATED);
    }

}
