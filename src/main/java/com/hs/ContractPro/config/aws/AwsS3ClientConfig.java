package com.hs.ContractPro.config.aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AwsS3ClientConfig {

    @Configuration
    @EnableConfigurationProperties({AwsS3ConfigProperties.class})
    public static class AwsS3MinioConfig {

        @Bean(name = "amazonS3Client")
        public AmazonS3Client amazonS3ClientLocal() {
            return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:19000", "ap-northeast-2")
                    ).withPathStyleAccessEnabled(true)
                    .withClientConfiguration(
                            new ClientConfiguration().withSignerOverride("AWSS3V4SignerType")
                    ).withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials("억세스키", "시크릿키")
                    )).build();
        }

    }


}
