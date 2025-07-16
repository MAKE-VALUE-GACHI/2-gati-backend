package com.gati.hankki.common.exception;

public class FileSaveFailedException extends RuntimeException {
    public FileSaveFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
