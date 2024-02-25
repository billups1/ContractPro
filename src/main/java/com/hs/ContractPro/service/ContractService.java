package com.hs.ContractPro.service;

import com.hs.ContractPro.common.exception.CustomException;
import com.hs.ContractPro.domain.contract.*;
import com.hs.ContractPro.domain.dto.contract.ContractCreateView;
import com.hs.ContractPro.domain.storage.StorageFile;
import com.hs.ContractPro.domain.team.Team;
import com.hs.ContractPro.domain.user.User;
import com.hs.ContractPro.web.dto.ContractCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final StorageFileService storageFileService;
    private final ContractJpaRepository contractJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;
    private final ContractCategoryJpaRepository contractCategoryJpaRepository;
    private final ContractUserJpaRepository contractUserJpaRepository;
    private final ContractTeamJpaRepository contractTeamJpaRepository;

    @Transactional
    public ContractCreateView create(ContractCreateRequest request) {

        Category category = categoryJpaRepository.findById(request.categoryId()).orElseThrow(() -> {
            throw new CustomException("없는 카테고리입니다.");
        });

        ContractCategory contractCategory = ContractCategory.builder()
                .category(category)
                .contract(null)
                .build();
        contractCategoryJpaRepository.save(contractCategory);

        Contract contract = contractJpaRepository.save(request.create(contractCategory));

        contractCategory.setContract(contract);


        ContractUser contractUser = ContractUser.builder()
                .contract(contract)
                .user(User.builder()
                        .id(request.userId())
                        .build())
                .build();
        contractUserJpaRepository.save(contractUser);

        ContractTeam contractTeam = ContractTeam.builder()
                .contract(contract)
                .team(Team.builder()
                        .id(request.teamId())
                        .build())
                .build();
        contractTeamJpaRepository.save(contractTeam);

        List<MultipartFile> multipartFiles = request.contractImages();
        ArrayList<StorageFile> storageFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            storageFiles.add(storageFileService.upload(multipartFile, contract));
        }

        // 에러 뜰듯, contract에 storageFiles을 set 해줘야 할듯..

        return ContractCreateView.from(contract);
    }
}
