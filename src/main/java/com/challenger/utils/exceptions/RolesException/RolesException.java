package com.challenger.utils.exceptions.RolesException;

import org.springframework.http.HttpStatus;

public class RolesException extends RuntimeException{
    private final HttpStatus httpStatus;

    public RolesException(MessageException messageException, HttpStatus httpStatus) {
        super(messageException.getMessageError(), null, true, false);
        this.httpStatus = httpStatus;
    }

    public int getHttpStaus(){
        return this.httpStatus.value();
    }
}
