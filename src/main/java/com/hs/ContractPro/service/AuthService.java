package com.hs.ContractPro.service;

import com.hs.ContractPro.application.auth.TokenProvider;
import com.hs.ContractPro.common.exception.CustomException;
import com.hs.ContractPro.domain.auth.RefreshToken;
import com.hs.ContractPro.domain.auth.RefreshTokenJpaRepository;
import com.hs.ContractPro.domain.auth.dto.TokenRequest;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.team.TeamJpaRepository;
import com.hs.ContractPro.domain.user.User;
import com.hs.ContractPro.domain.user.UserJpaRepository;
import com.hs.ContractPro.domain.user.dto.SignUpView;
import com.hs.ContractPro.web.auth.dto.LoginRequest;
import com.hs.ContractPro.web.auth.dto.TokenDto;
import com.hs.ContractPro.web.auth.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final TeamJpaRepository teamJpaRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public SignUpView create(UserCreateRequest request) {

        Team team = teamJpaRepository.findById(request.teamId()).orElseThrow(() -> {
            throw new CustomException("없는 팀입니다.");
        });

        return SignUpView.from(userJpaRepository.save(request.create(team, null, bCryptPasswordEncoder.encode(request.password()))), null);
    }

    public TokenDto login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(request.loginId())
                .value(tokenDto.refreshToken())
                .build();
        refreshTokenJpaRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto reissue(TokenRequest request) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(request.refreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(request.accessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenJpaRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치 검사
        if (!refreshToken.getValue().equals(request.refreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.refreshToken());
        refreshTokenJpaRepository.save(newRefreshToken);

        return tokenDto;
    }
}
