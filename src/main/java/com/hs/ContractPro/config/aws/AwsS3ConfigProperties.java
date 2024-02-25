package com.hs.ContractPro.config.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "aws")
public class AwsS3ConfigProperties {

    private final S3Properties s3;

    private final CredentialProperties credential;

    private final RegionProperties region;

    @ConstructorBinding
    public AwsS3ConfigProperties(S3Properties s3, CredentialProperties credential, RegionProperties region) {
        this.s3 = s3;
        this.credential = credential;
        this.region = region;
    }

    public S3Properties getS3() {
        return s3;
    }

    public CredentialProperties getCredential() {
        return credential;
    }

    public RegionProperties getRegion() {
        return region;
    }

    public static class S3Properties {
        String bucketPublic;
        String bucketPrivate;

        public String getBucketPublic() {
            return bucketPublic;
        }

        public void setBucketPublic(String bucketPublic) {
            this.bucketPublic = bucketPublic;
        }

        public String getBucketPrivate() {
            return bucketPrivate;
        }

        public void setBucketPrivate(String bucketPrivate) {
            this.bucketPrivate = bucketPrivate;
        }
    }

    public static class CredentialProperties {
        String accessKey;
        String secretKey;

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }

    public static class RegionProperties {
        String location;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

}
