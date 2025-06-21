package org.aryak.batch.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoSuchClientException extends RuntimeException {
    private int code;
    private String message;


    public NoSuchClientException(Long clientId) {
    }
}
