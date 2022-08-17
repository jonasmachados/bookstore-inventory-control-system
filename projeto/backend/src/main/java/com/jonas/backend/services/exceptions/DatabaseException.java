package com.jonas.backend.services.exceptions;

/**
 *
 * @author Jonas created
 */
//Exception specific of services, SUBCLASSE RUNTIMEEXCEPTION

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //Constructor
    public DatabaseException(String msg) {
        super(msg);
    }

}
