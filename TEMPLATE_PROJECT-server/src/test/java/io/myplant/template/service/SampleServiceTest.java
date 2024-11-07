package io.myplant.template.service;

import static org.junit.jupiter.api.Assertions.*;

import io.micrometer.common.util.StringUtils;

import org.junit.jupiter.api.Test;

class SampleServiceTest {
    private final SampleService sampleService = new SampleService();

    @Test
    void sampleTest() {
        assertTrue(StringUtils.isNotBlank(sampleService.getResult(1).getMsg()));
    }
}
