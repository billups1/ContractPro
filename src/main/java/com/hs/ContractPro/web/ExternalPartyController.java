package com.hs.ContractPro.web;

import com.hs.ContractPro.service.ExternalPartyService;
import com.hs.ContractPro.web.common.dto.ApiResponse;
import com.hs.ContractPro.web.dto.ExternalPartyCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExternalPartyController {

    private final ExternalPartyService externalPartyService;

    @PostMapping("/api/externalParty")
    public ResponseEntity create(@RequestBody @Valid ExternalPartyCreateRequest request) {
        externalPartyService.join(request);
        return new ResponseEntity(new ApiResponse<>(1, "외부당사자 생성 성공"), HttpStatus.CREATED);
    }

}
