package com.hs.ContractPro.web.dto;

import com.hs.ContractPro.domain.externalParty.ExternalParty;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record ExternalPartyCreateRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        String phone,
        String bio,
        @NotEmpty
        ExternalParty.TYPE type,
        String companyRegistrationNumber

) {
        public ExternalParty create() {
                return ExternalParty.builder()
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .bio(bio)
                        .type(type)
                        .companyRegistrationNumber(companyRegistrationNumber)
                        .build();
        }
}
