package com.hs.ContractPro.web;

import com.hs.ContractPro.service.UserService;
import com.hs.ContractPro.web.common.dto.ApiResponse;
import com.hs.ContractPro.web.dto.UserJoinRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("api/user/")
    public ResponseEntity join(@RequestBody @Valid UserJoinRequest request) {
        userService.join(request);
        return new ResponseEntity(new ApiResponse<>(1, "성공"), HttpStatus.CREATED);
    }

}
