package com.gapperdan.hswm.exception;

import com.gapperdan.hswm.view.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    public @ResponseBody ErrorResource handleCountryNotFound(CountryNotFoundException exception) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setCode(HttpStatus.NOT_FOUND.toString());
        errorResource.setMessage(exception.getMessage());
        return errorResource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody List<ErrorResource> handleValidationException(MethodArgumentNotValidException ex) throws IOException {
        ErrorResource errorResource;
        List<ErrorResource> errorResourceList = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getFieldErrors()) {
            errorResource = new ErrorResource();
            errorResource.setCode(error.getCode());
            errorResource.setMessage(error.getDefaultMessage());
            errorResourceList.add(errorResource);
        }

        return errorResourceList;
    }
}