package com.hs.ContractPro.web.auth.dto;

import lombok.Getter;

public record TokenDto (
        String grantType,
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn
) {
}
