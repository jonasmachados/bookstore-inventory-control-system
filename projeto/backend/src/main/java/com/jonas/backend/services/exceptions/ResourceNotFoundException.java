
package com.jonas.backend.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    private static final long serialVersionUID = 1l;
    
    //Exception personalizada, execption to handling erro consult by ID
    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " + id);
    }
}
