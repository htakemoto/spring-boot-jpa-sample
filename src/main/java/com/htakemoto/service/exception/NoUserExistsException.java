package com.htakemoto.service.exception;

public class NoUserExistsException extends RuntimeException {

    public NoUserExistsException(final String message) {
        super(message);
    }
}
