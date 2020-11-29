package com.mygroup.project.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super("Data not found");
    }
}
