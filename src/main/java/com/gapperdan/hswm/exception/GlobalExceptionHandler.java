package com.gapperdan.hswm.exception;

import com.gapperdan.hswm.view.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseBody
    public ErrorResource handleCountryNotFound(CountryNotFoundException exception) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setCode(HttpStatus.NOT_FOUND.toString());
        errorResource.setMessage(exception.getMessage());
        return errorResource;
    }

}
