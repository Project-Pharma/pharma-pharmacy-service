package com.inoastrum.pharmapharmacyservice.exceptions;

public class PharmacyNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Pharmacy that you are looking for doesn't exist";
    }
}
