package com.hs.ContractPro.domain.user.dto;

import com.hs.ContractPro.domain.user.User;

public record SignUpView(
        Long id,
        String loginId,
        String email,
        String jwt
) {
    public static SignUpView from(User user, String jwt) {
        return new SignUpView(
                user.getId(),
                user.getLoginId(),
                user.getEmail(),
                jwt
        );
    }
}
