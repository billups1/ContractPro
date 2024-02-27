package com.hs.ContractPro.web;

import com.hs.ContractPro.domain.auth.dto.TokenRequest;
import com.hs.ContractPro.domain.team.dto.TeamCreateView;
import com.hs.ContractPro.domain.user.dto.SignUpView;
import com.hs.ContractPro.service.AuthService;
import com.hs.ContractPro.service.TeamService;
import com.hs.ContractPro.web.auth.dto.LoginRequest;
import com.hs.ContractPro.web.auth.dto.TokenDto;
import com.hs.ContractPro.web.auth.dto.UserCreateRequest;
import com.hs.ContractPro.web.common.dto.ApiResponse;
import com.hs.ContractPro.web.team.dto.TeamCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/api/team")
    public ResponseEntity create(@RequestBody @Valid TeamCreateRequest request) {
        TeamCreateView view = teamService.create(request);
        return new ResponseEntity(new ApiResponse<>(1, "팀 생성 성공", view), HttpStatus.CREATED);
    }


}
