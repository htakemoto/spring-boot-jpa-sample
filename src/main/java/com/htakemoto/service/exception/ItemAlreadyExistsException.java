package com.htakemoto.service.exception;

public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException(final String message) {
        super(message);
    }
}
