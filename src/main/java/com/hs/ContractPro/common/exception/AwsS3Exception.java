package com.hs.ContractPro.common.exception;

public class AwsS3Exception extends RuntimeException{
    public AwsS3Exception(Throwable cause) {
        super(cause);
    }

    public AwsS3Exception(String message) {
        super(message);
    }

    public AwsS3Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
