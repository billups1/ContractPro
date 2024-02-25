package com.hs.ContractPro.domain.externalParty;

import com.hs.ContractPro.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalPartyJpaRepository extends JpaRepository<ExternalParty, Long> {
}
