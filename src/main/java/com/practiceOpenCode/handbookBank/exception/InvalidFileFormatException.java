package com.practiceOpenCode.handbookBank.exception;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String message){
        super(message);
    }
}