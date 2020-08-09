package com.inoastrum.pharmapharmacyservice.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorObject {
    private final String message;
}
