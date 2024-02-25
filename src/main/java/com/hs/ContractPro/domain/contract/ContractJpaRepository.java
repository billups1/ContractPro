package com.hs.ContractPro.domain.contract;

import com.hs.ContractPro.domain.externalParty.ExternalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJpaRepository extends JpaRepository<Contract, Long> {
}
