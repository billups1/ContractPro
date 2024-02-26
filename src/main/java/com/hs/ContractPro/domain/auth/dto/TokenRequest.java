package com.hs.ContractPro.domain.auth.dto;

public record TokenRequest(
        String accessToken,
        String refreshToken
) {
}
