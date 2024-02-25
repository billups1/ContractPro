package com.hs.ContractPro.service;

import com.hs.ContractPro.application.storage.FileUtil;
import com.hs.ContractPro.config.aws.AwsS3ConfigProperties;
import com.hs.ContractPro.domain.contract.Contract;
import com.hs.ContractPro.domain.storage.StorageFile;
import com.hs.ContractPro.domain.storage.StorageFileJpaRepository;
import com.hs.ContractPro.web.aws.dto.S3FileDto;
import com.hs.ContractPro.web.dto.ContractCreateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageFileService {

    private final FileUtil fileUtil;
    private final AwsS3ConfigProperties awsS3ConfigProperties;
    private final StorageFileJpaRepository storageFileJpaRepository;

    @Transactional
    public StorageFile upload(@NotNull MultipartFile file, Contract contract) {

        String bucketPublic = awsS3ConfigProperties.getS3().getBucketPublic();
        String folder = generateFolder("files", "/");
        String uuid = UUID.randomUUID().toString();

        S3FileDto s3FileDto = fileUtil.upload(bucketPublic, folder, uuid, file);


        StorageFile storageFile = StorageFile.builder()
                .contract(contract)
                .originalFileName(file.getOriginalFilename())
                .fileType(s3FileDto.fileExtension())
                .bucket(s3FileDto.bucket())
                .savedFullPathName(s3FileDto.fullPath())
                .s3Url(s3FileDto.s3Url())
                .build();

        StorageFile savedStorageFile = storageFileJpaRepository.save(storageFile);
        return savedStorageFile;
    }

    private static String generateFolder(String prefix, String separator) {
        return Path.of(prefix, separator).toString();
    }

}
