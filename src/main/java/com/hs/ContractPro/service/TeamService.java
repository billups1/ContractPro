package com.hs.ContractPro.service;

import com.hs.ContractPro.common.exception.CustomException;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.team.TeamJpaRepository;
import com.hs.ContractPro.domain.team.dto.TeamCreateView;
import com.hs.ContractPro.web.team.dto.TeamCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamJpaRepository teamJpaRepository;

    @Transactional
    public TeamCreateView create(TeamCreateRequest request) {

        Team team = Team.builder()
                .name(request.name())
                .build();

        if (request.upperTeamId() != null) {
            Team upperTeam = teamJpaRepository.findById(request.upperTeamId()).orElseThrow(() -> {
                throw new CustomException("없는 팀입니다.");
            });
            upperTeam.getTeams().add(team);
        }

        teamJpaRepository.save(team);

        return TeamCreateView.from(team);

    }

}
