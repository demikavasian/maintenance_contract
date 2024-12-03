package io.myplant.maintenancecontract.service;

import io.myplant.maintenancecontact.api.model.*;
import io.myplant.maintenancecontract.mapper.*;
import io.myplant.maintenancecontract.model.AssetEntity;
import io.myplant.maintenancecontract.model.ContractEntity;
import io.myplant.maintenancecontract.repository.ContractRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ContractService {

    private final ContractMapper contractMapper;
    private final ContractRepository contractRepository;
    private final GuaranteeMapper guaranteeMapper;
    private final AssetMapper assetMapper;
    private final ScopeMapper scopeMapper;
    private final AdditionalScopeMapper additionalScopeMapper;

    public Contract saveContract(@Valid Contract contract) {
        try {
            log.info("Saving contract with name: {}", contract.getName());
            ContractEntity contractEntity = contractMapper.toEntity(contract);

            if (contract.getAssets() != null && !contract.getAssets().isEmpty()) {
                contract.getAssets()
                        .stream()
                        .map(this::fillAssetEntity)
                        .forEach(contractEntity::addAssetEntity);
            }

            if (contract.getContractAdditionalScope() != null && !contract.getContractAdditionalScope().isEmpty()) {
                setAdditionalScopeToContractEntity(contractEntity, contract.getContractAdditionalScope());
            }

            return contractMapper.toResponse(contractRepository.save(contractEntity));
        }catch (Exception e){
            log.error("Error while saving contract with name: {}", contract.getName());
            throw new RuntimeException("Error while saving contract");
        }
    }

    private AssetEntity fillAssetEntity(Asset asset) {
        AssetEntity assetEntity = assetMapper.toEntity(asset);

        if (asset.getGuarantees() != null && !asset.getGuarantees().isEmpty()) {
            setGuaranteesToAssetEntity(assetEntity, asset.getGuarantees());
        }

        if (asset.getAssetAdditionalScope() != null && !asset.getAssetAdditionalScope().isEmpty()) {
            setAdditionalScopeToAssetEntity(assetEntity, asset.getAssetAdditionalScope());
        }

        if (asset.getScopes() != null && !asset.getScopes().isEmpty()) {
            setScopesToAssetEntity(assetEntity, asset.getScopes());
        }

        return assetEntity;
    }

    private void setAdditionalScopeToAssetEntity(AssetEntity assetEntity, List<AssetAdditionalScope> assetAdditionalScope) {
        assetAdditionalScope.stream().map(additionalScopeMapper::assetAdditionalScopeToEntity).forEach(assetEntity::addAdditionalScopeEntity);
    }

    private void setAdditionalScopeToContractEntity(ContractEntity contractEntity, List<ContractAdditionalScope> contractAdditionalScope) {
        contractAdditionalScope.stream().map(additionalScopeMapper::contractAdditionalScopeToEntity).forEach(contractEntity::addAdditionalScopeEntity);
    }

    private void setGuaranteesToAssetEntity(AssetEntity assetEntity, List<Guarantee> guarantees) {
        guarantees.stream().map(guaranteeMapper::toEntity).forEach(assetEntity::addGuaranteeEntity);
    }

    private void setScopesToAssetEntity(AssetEntity assetEntity, List<Scope> scopes) {
        scopes.stream().map(scopeMapper::toEntity).forEach(assetEntity::addScopeEntity);
    }

    public Page<Contract> getAllContract(Pageable pageable) {
        return contractRepository.findAll(pageable).map(contractMapper::toResponse);
    }
}
