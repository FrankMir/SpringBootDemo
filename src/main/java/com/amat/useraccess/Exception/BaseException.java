package com.amat.useraccess.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public abstract class BaseException extends RuntimeException {
    private Object errorData;

    public BaseException(String message) {super(message);}

    public BaseException(String message, Throwable cause) {super(message, cause);}

    public abstract HttpStatus getStatus();

    public Object getErrData() {
        return errorData;
    }

    public BaseException setErrorData(@NonNull Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
