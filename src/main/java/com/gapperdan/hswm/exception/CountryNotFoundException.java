package com.gapperdan.hswm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String name) {
        super("Country '"+name+"' not found");
    }
}