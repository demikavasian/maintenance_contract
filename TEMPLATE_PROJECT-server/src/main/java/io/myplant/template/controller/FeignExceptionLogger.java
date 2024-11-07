package io.myplant.template.controller;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class FeignExceptionLogger {

    /**
     * In case of a feign exception (service sending a response other than 200),
     * log both the status code and all data in the response body.
     *
     * @param e
     */
    @ExceptionHandler(FeignException.class)
    public void handleFeignException(FeignException e) {
        log.error("received feign exception: status={}, response body content={}",
                  e.status(), e.contentUTF8());
        throw e;
    }
}
