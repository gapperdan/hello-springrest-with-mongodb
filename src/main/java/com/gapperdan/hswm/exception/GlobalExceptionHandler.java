package com.gapperdan.hswm.exception;

import com.gapperdan.hswm.view.ErrorResource;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public @ResponseBody ErrorResource handleServiceException(Exception ex) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setCode(HttpStatus.SERVICE_UNAVAILABLE.toString());
        errorResource.setMessage("Service is currently unavailable, please try again later.");
        log.error("Service error: "+ex.getMessage());

        return errorResource;
    }
}