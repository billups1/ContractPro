package com.hs.ContractPro.web.dto;

import com.hs.ContractPro.domain.contract.Contract;
import com.hs.ContractPro.domain.contract.Category;
import com.hs.ContractPro.domain.contract.ContractCategory;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public record ContractCreateRequest(
        @NotEmpty
        String name,
        @NotEmpty
        Long categoryId,
        @NotEmpty
        Long userId,
        @NotEmpty
        Long teamId,
        @NotEmpty
        Long externalPartyId,
        LocalDateTime contractSealDate,
        LocalDateTime contractEffectiveDate,
        LocalDateTime contractEndDate,
        List<MultipartFile> contractImages,
        Contract.CONTRACT_METHOD contractMethod

) {
        public Contract create(ContractCategory contractCategory) {
                return Contract.builder()
                        .name(name)
                        .contractCategory(contractCategory)
                        .contractSealDate(contractSealDate)
                        .contractEffectiveDate(contractEffectiveDate)
                        .contractEndDate(contractEndDate)
                        .contractMethod(contractMethod)
                        .build();
        }
}
