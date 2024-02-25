package com.hs.ContractPro.domain.dto.contract;

import com.hs.ContractPro.domain.storage.StorageFile;

public record ContractCreateStorageFileView(
        Long id,
        String originalFileName,
        String fileType,
        String bucket,
        String s3Url
) {
    public static ContractCreateStorageFileView from(StorageFile storageFile) {
        return new ContractCreateStorageFileView(
                storageFile.getId(),
                storageFile.getOriginalFileName(),
                storageFile.getFileType(),
                storageFile.getBucket(),
                storageFile.getS3Url()
        );
    }

}
