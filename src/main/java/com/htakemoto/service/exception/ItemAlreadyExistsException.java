package com.htakemoto.service.exception;

public class ItemAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public ItemAlreadyExistsException(final String message) {
        super(message);
    }
}
