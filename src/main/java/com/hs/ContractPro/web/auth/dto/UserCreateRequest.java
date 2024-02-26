package com.hs.ContractPro.web.auth.dto;

import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record UserCreateRequest(
        Long teamId,
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        @NotEmpty
        String loginId,
        @NotEmpty
        String password,
        MultipartFile profileImage

) {
        public User create(Team team, String profileImageUrl, String encodedPassword) {
                return User.builder()
                        .loginId(loginId)
                        .email(email)
                        .team(team)
                        .password(encodedPassword)
                        .name(name)
                        .profileImageUrl(profileImageUrl)
                        .build();
        }
}
