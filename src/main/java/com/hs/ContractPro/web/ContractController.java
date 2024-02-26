package com.hs.ContractPro.web;

import com.hs.ContractPro.domain.dto.contract.ContractCreateView;
import com.hs.ContractPro.service.ContractService;
import com.hs.ContractPro.web.common.dto.ApiResponse;
import com.hs.ContractPro.web.dto.ContractCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/api/user/")
    public ResponseEntity create(@RequestBody @Valid ContractCreateRequest request) {
        ContractCreateView view = contractService.create(request);
        return new ResponseEntity(new ApiResponse<>(1, "계약 생성 성공", view), HttpStatus.CREATED);
    }

}
