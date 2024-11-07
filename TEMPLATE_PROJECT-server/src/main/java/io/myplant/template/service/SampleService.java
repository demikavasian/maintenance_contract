package io.myplant.template.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
public class SampleService {

    public Result getResult(long arg) {
        return new Result(String.format("GET me if you can - arg was %s", arg));
    }


    @Getter
    @AllArgsConstructor
    public static class Result {

        private String msg;
    }
}
