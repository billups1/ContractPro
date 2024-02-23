package com.hs.ContractPro.domain.contract;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "externalParties")
public class ContractTeam extends BaseTimeEntity {

    @EmbeddedId
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
