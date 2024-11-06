package com.bluemoon.gradius.exception;

import lombok.Getter;

@Getter
public class isLockedException extends RuntimeException {

    public isLockedException(String errorMessage) {
        super(errorMessage);
    }
}
