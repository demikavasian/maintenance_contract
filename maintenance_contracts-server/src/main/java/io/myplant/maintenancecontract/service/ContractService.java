package io.myplant.maintenancecontract.service;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.mapper.ContractMapper;
import io.myplant.maintenancecontract.model.ContractEntity;
import io.myplant.maintenancecontract.repository.ContractRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ContractService {

    private final ContractMapper contractMapper;
    private final ContractRepository contractRepository;

    public ResponseEntity<Contract> saveContract(@Valid Contract contract) {
        ContractEntity contractEntity = contractMapper.toEntity(contract);
        return ResponseEntity.ok(contractMapper.toResponse(contractRepository.save(contractEntity)));
    }

    public Page<Contract> getAllContract(Pageable pageable) {
        return contractRepository.findAll(pageable).map(contractMapper::toResponse);
    }
}
