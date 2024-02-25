package com.hs.ContractPro.domain.storage;

import com.hs.ContractPro.domain.contract.Contract;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "storageFiles")
public class StorageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractId")
    private Contract contract;

    @Column(name = "original_file_name", columnDefinition = "VARCHAR(100) COMMENT '원본파일이름'")
    private String originalFileName;

    @Column(name = "file_type", columnDefinition = "VARCHAR(20) COMMENT '파일타입'")
    private String fileType;

    @Column(name = "bucket", columnDefinition = "VARCHAR(65) COMMENT '버킷이름'")
    private String bucket;

    @Column(name = "saved_full_path_name", columnDefinition = "VARCHAR(500) COMMENT 's3 object key 저장된전체경로및파일이름포함'")
    private String savedFullPathName;

    @Column(name = "s3_url", columnDefinition = "VARCHAR(500) COMMENT 's3 파일 url'")
    private String s3Url;

    public static StorageFile newOne(
            String originalFileName,
            String fileType,
            String bucket,
            String savedFullPathName,
            String s3Url) {

        var storageFile = new StorageFile();
        storageFile.originalFileName = originalFileName;
        storageFile.fileType = fileType;
        storageFile.bucket = bucket;
        storageFile.savedFullPathName = savedFullPathName;
        storageFile.s3Url = s3Url;

        return storageFile;
    }

    public String getKey() {
        return savedFullPathName;
    }

}
