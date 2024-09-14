package org.e2cho.e2cho_HW.exception;

import lombok.Getter;
import org.e2cho.e2cho_HW.constant.ErrorType;

@Getter
public class CustomErrorException extends RuntimeException{
    private final ErrorType errorType;
    public CustomErrorException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
