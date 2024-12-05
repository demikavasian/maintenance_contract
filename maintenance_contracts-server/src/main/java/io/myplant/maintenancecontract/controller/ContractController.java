package io.myplant.maintenancecontract.controller;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontact.api.validation.ValidContract;
import io.myplant.maintenancecontact.api.validation.ValidScope;
import io.myplant.maintenancecontract.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority('Admin', 'Internal')")
    public ResponseEntity<Page<Contract>> getContract(Pageable pageable) {
        return ResponseEntity.ok(contractService.getAllContract(pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<Contract> saveContract(@Valid @RequestBody @ValidContract @ValidScope Contract contract) {
        return ResponseEntity.ok(contractService.saveContract(contract));
    }


}
