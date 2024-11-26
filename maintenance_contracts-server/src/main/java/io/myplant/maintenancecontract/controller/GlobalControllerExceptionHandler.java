package io.myplant.maintenancecontract.controller;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return handleExceptionInternal(
                ex, new ErrorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        logger.error("Caught exception on the controller level", ex);
        return handleExceptionInternal(
                ex, new ErrorMessage(ex), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationException(
            ConstraintViolationException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {FeignException.class})
    protected ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, ex, new HttpHeaders(), HttpStatus.valueOf(ex.status()), request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeExceptions(
            RuntimeException ex, WebRequest request) {
        logger.error("Caught exception on the controller level", ex);
        return handleExceptionInternal(
                ex,
                new ErrorMessage(ex),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    protected ResponseEntity<Object> handleResponseStatusExceptions(
            ResponseStatusException ex, WebRequest request) {

        UUID uuid = UUID.randomUUID();
        if (ex.getStatusCode().is4xxClientError()) {
            logger.warn(
                    "Response status exception ("
                            + uuid
                            + ") "
                            + ex.getStatusCode()
                            + ": "
                            + ex.getReason());
        } else if (ex.getStatusCode().is5xxServerError()) {
            logger.error("Response status exception (" + uuid + "): " + ex);
        }

        return handleExceptionInternal(
                ex,
                new ErrorMessage(
                        String.valueOf(ex.getStatusCode()), ex.getReason(), uuid.toString()),
                ex.getHeaders(),
                ex.getStatusCode(),
                request);
    }

    @Builder
    @Getter
    @RequiredArgsConstructor
    public static class ErrorMessage {
        private final String error;
        private final String message;
        private final String uuid;
        private final Long timestamp = System.currentTimeMillis();

        ErrorMessage(RuntimeException ex) {
            this.error = ex.getClass().getSimpleName();
            this.message = ex.getMessage();
            this.uuid = null;
        }

        ErrorMessage(MethodArgumentNotValidException ex) {
            this.error = ex.getClass().getSimpleName();
            this.message = ex.getMessage();
            this.uuid = null;
        }
    }
}
