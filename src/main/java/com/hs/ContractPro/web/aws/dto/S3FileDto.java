package com.hs.ContractPro.web.aws.dto;

public record S3FileDto (
        String originalFilename,
        String fileExtension,
        String bucket,
        String fullPath,
        String s3Url
) {

}
