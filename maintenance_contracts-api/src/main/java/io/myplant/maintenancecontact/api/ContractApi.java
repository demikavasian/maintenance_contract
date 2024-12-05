package io.myplant.maintenancecontact.api;

import io.myplant.maintenancecontact.api.model.Contract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@FeignClient(
        name = "maintenance-contracts-contract-api",
        url = "${services.maintenance-contracts.url}",
        path = "/contract")
public interface ContractApi {

    @GetMapping(value = "/get-all", produces = APPLICATION_JSON_VALUE)
    Page<Contract> getAllContracts(
            @RequestHeader(value = "x-seshat-token") String token,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort", required = false) String sort);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Contract createContract(
            @RequestHeader(value = "x-seshat-token") String token, @RequestBody Contract contract);

}
