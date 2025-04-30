package com.tms.dataprovider.core.exceptions;

import lombok.Getter;

@Getter
public class DataServiceException extends Exception {

    private int code;
    public DataServiceException(int code, String message){
        super(message);
    }
}
