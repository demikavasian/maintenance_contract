package io.myplant.maintenancecontract.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(
            ConstraintViolationException exception, ServletWebRequest webRequest)
            throws IOException {
        var response = webRequest.getResponse();
        if (response != null) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        }
    }
}
