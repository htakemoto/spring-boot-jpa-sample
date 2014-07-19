package com.htakemoto.service.exception;

public class NoItemExistsException extends RuntimeException {

    public NoItemExistsException(final String message) {
        super(message);
    }
}
