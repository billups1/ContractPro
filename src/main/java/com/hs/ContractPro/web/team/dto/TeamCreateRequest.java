package com.hs.ContractPro.web.team.dto;

import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record TeamCreateRequest(
        @NotEmpty
        String name,
        Long upperTeamId

) {
        public Team create(Team upperTeam) {
                return Team.builder()
                        .name(name)
                        .team(upperTeam)
                        .build();
        }
}
