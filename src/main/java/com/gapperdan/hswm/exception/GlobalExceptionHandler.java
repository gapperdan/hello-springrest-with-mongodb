package com.gapperdan.hswm.exception;

import com.gapperdan.hswm.view.ErrorResource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorResource errorResource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    public @ResponseBody ErrorResource handleCountryNotFound(CountryNotFoundException exception) {
        errorResource.setCode(HttpStatus.NOT_FOUND.toString());
        errorResource.setMessage(exception.getMessage());
        errorResource.generateRefId();
        log.error("Ref: ("+errorResource.getRef()+"): "+errorResource.getMessage());
        return errorResource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody List<ErrorResource> handleValidationException(MethodArgumentNotValidException ex) throws IOException {
        List<ErrorResource> errorResourceList = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getFieldErrors()) {
            errorResource = new ErrorResource();
            errorResource.setCode(error.getCode());
            errorResource.setMessage(error.getDefaultMessage());
            errorResource.generateRefId();
            errorResourceList.add(errorResource);
            log.error("Ref: ("+errorResource.getRef()+"): "+errorResource.getMessage());
        }

        return errorResourceList;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public @ResponseBody ErrorResource handleServiceException(Exception ex) {
        errorResource.setCode(HttpStatus.SERVICE_UNAVAILABLE.toString());
        errorResource.setMessage("Service is currently unavailable, please try again later.");
        errorResource.generateRefId();
        log.error("Ref:("+errorResource.getRef()+"): "+errorResource.getMessage());
        log.error("Ref:("+errorResource.getRef()+"): "+ex.getMessage());
        return errorResource;
    }
}