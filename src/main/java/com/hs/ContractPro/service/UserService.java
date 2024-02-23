package com.hs.ContractPro.service;

import com.hs.ContractPro.common.exception.CustomException;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.team.TeamJpaRepository;
import com.hs.ContractPro.domain.user.UserJpaRepository;
import com.hs.ContractPro.web.dto.UserJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final TeamJpaRepository teamJpaRepository;

    public void join(UserJoinRequest request) {

        Team team = teamJpaRepository.findById(request.teamId()).orElseThrow(() -> {
            throw new CustomException("없는 팀입니다.");
        });

        userJpaRepository.save(request.create(team, null));
    }
}
