package com.jonas.backend.services.exceptions;

public class DateParsingException extends RuntimeException{
    
    private static final long serialVersionUID = 1l;

    public DateParsingException(String message) {
        super(message);
    }
 
}
