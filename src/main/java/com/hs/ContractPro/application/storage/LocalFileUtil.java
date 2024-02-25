package com.hs.ContractPro.application.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hs.ContractPro.common.exception.AwsS3Exception;
import com.hs.ContractPro.web.aws.dto.S3FileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class LocalFileUtil implements FileUtil {

    private static final String FORMAT_S3_OBJECT_KEY = "%s/%s.%s";
    private static final String FORMAT_S3_URL = "https://localhost:19000/%s/%s";

    private final AmazonS3Client amazonS3Client;

    public LocalFileUtil(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public S3FileDto upload(String bucket, String path, String replacedFileName, MultipartFile multipartFile) {
        String fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        if (fileExtension == null) {
            throw new IllegalArgumentException(String.format("원본파일명(%s)에서 파일확장자를 찾을 수 없습니다.", multipartFile.getOriginalFilename()));
        }

        String fullPath = getS3ObjectKey(path, replacedFileName, fileExtension);
        try (InputStream fileIo = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket,
                    fullPath,
                    fileIo,
                    getObjectMetadata(multipartFile)));

            String s3Url = getS3Url(bucket, fullPath);
            return new S3FileDto(multipartFile.getOriginalFilename(), fileExtension, bucket, fullPath, s3Url);
        } catch (Exception e) {
            throw new AwsS3Exception(e);
        }
    }

    private static ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        return metadata;
    }

    private static String getS3Url(String bucket, String objectKey) {
        return String.format(FORMAT_S3_URL, bucket, objectKey);
    }

    private static String getS3ObjectKey(String path, String filename, String fileExtension) {
        return String.format(FORMAT_S3_OBJECT_KEY, path, filename, fileExtension);
    }

}
