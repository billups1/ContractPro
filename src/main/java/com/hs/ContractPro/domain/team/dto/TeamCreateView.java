package com.hs.ContractPro.domain.team.dto;

import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;

public record TeamCreateView(
        String teamName
) {
    public static TeamCreateView from(Team team) {
        return new TeamCreateView(
                team.getName()
        );
    }
}
