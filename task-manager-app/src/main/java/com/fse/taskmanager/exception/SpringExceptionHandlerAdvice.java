package com.fse.taskmanager.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class SpringExceptionHandlerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, List<ErrorDetails>> handleException(HttpRequestMethodNotSupportedException exception) {
        return createErrorData(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, List<ErrorDetails>> handleException(MethodArgumentTypeMismatchException exception) {
        return createErrorData("invalid type: " + exception.getValue().toString());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, List<ErrorDetails>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return createErrorData(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, List<ErrorDetails>> handleException(HttpMessageNotReadableException exception) {
        return createErrorData("invalid request");
    }

    static Map<String, List<ErrorDetails>> createErrorData(String msg) {
        return createErrorsMap(new ErrorDetails(msg));
    }

    static Map<String, List<ErrorDetails>> createErrorsMap(List<ErrorDetails> errors) {
        Map<String,  List<ErrorDetails>> map = new HashMap<>();
        map.put("errors", errors);
        return map;
    }

    static Map<String, List<ErrorDetails>> createErrorsMap(ErrorDetails error) {
        return createErrorsMap(Arrays.asList(error));
    }
}
