package io.myplant.template.controller;

import io.myplant.template.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/{assetId}")
    public SampleService.Result getSample(@PathVariable("assetId") long assetId) {
        return service.getResult(assetId);
    }
}
