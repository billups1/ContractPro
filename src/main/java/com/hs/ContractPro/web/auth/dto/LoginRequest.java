package com.hs.ContractPro.web.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequest(
        @NotEmpty
        String loginId,
        @NotEmpty
        String password
) {
        public UsernamePasswordAuthenticationToken toAuthentication() {
               return new UsernamePasswordAuthenticationToken(loginId, password);
        }
}
