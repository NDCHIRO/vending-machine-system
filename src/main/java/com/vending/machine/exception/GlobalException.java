package com.vending.machine.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalException extends RuntimeException {

    public GlobalException(String message)
    {
        super(message);
    }
}