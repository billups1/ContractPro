package com.hs.ContractPro.application.storage;

import com.hs.ContractPro.web.aws.dto.S3FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileUtil {

    S3FileDto upload(String bucket, String path, String replacedFileName, MultipartFile multipartFile);

}
