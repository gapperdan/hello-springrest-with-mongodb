package com.gapperdan.hswm.exception;

import com.gapperdan.hswm.view.ErrorResource;
import com.google.common.collect.Maps;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
    public @ResponseBody Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) throws IOException {
        Map<String, Object>  map = Maps.newHashMap();
        map.put("error", "Validation Failure");
        map.put("violations", convertConstraintViolation(ex));
        return map;
    }

    private Map<String, Map<String, Object> > convertConstraintViolation(MethodArgumentNotValidException ex) {
        Map<String, Map<String, Object> > result = Maps.newHashMap();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            Map<String, Object>  violationMap = Maps.newHashMap();
            violationMap.put("target", ex.getBindingResult().getTarget());
            violationMap.put("type", ex.getBindingResult().getTarget().getClass());
            violationMap.put("message", error.getDefaultMessage());
            result.put(error.getObjectName(), violationMap);
        }
        return result;
    }

}
