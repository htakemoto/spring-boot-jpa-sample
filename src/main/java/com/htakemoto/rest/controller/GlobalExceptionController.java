package com.htakemoto.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.htakemoto.service.exception.ItemAlreadyExistsException;
import com.htakemoto.service.exception.NoItemExistsException;
import com.htakemoto.service.exception.NoUserExistsException;
import com.htakemoto.service.exception.UserAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleUserAlreadyExistsException(HttpServletRequest request, UserAlreadyExistsException e) {
        LOGGER.info("UserAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getMessage();
    }
    
    @ExceptionHandler(NoUserExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNonExistingUserException(HttpServletRequest request, NoUserExistsException e) {
        LOGGER.info("NoUserExistsException occured: URL="+request.getRequestURL());
        return e.getMessage();
    }
    
    @ExceptionHandler(ItemAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleItemAlreadyExistsException(HttpServletRequest request, ItemAlreadyExistsException e) {
        LOGGER.info("ItemAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getMessage();
    }
    
    @ExceptionHandler(NoItemExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNonExistingItemException(HttpServletRequest request, NoItemExistsException e) {
        LOGGER.info("NoItemExistsException occured: URL="+request.getRequestURL());
        return e.getMessage();
    }
}
