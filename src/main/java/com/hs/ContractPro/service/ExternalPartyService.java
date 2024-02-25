package com.hs.ContractPro.service;

import com.hs.ContractPro.domain.externalParty.ExternalPartyJpaRepository;
import com.hs.ContractPro.web.dto.ExternalPartyCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExternalPartyService {

    private final ExternalPartyJpaRepository externalPartyJpaRepository;

    @Transactional
    public void join(ExternalPartyCreateRequest request) {
        externalPartyJpaRepository.save(request.create());
    }
}
