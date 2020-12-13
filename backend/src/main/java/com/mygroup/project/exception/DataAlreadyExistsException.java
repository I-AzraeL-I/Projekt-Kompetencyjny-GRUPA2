package com.mygroup.project.exception;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String data) {
        super(String.format("Data %s already exists", data));
    }
}
