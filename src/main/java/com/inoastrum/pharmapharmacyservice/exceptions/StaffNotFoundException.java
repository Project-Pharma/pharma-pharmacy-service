package com.inoastrum.pharmapharmacyservice.exceptions;

public class StaffNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Staff you are looking for doesn't exist";
    }
}
