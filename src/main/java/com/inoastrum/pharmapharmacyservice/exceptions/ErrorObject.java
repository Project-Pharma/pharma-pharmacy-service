package com.inoastrum.pharmapharmacyservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
    private final String message;
    private final Class<? extends Exception> errorClass;

    public ErrorObject(String message, Class<? extends Exception> errorClass) {
        this.message = message;
        this.errorClass = errorClass;
    }
}
