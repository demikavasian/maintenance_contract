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
            setAdditionalScopeToContractEntity(contractEntity, contract.getContractAdditionalScope());

            return contractMapper.toResponse(contractRepository.save(contractEntity));
        }catch (Exception e){
            log.error("Error while saving contract with name: {}", contract.getName());
            throw new RuntimeException("Error while saving contract");
        }
    }

    public AssetEntity fillAssetEntity(Asset asset) {
        AssetEntity assetEntity = assetMapper.toEntity(asset);
        setGuaranteesToAssetEntity(assetEntity, asset.getGuarantees());
        setAdditionalScopeToAssetEntity(assetEntity, asset.getAssetAdditionalScope());
        setScopesToAssetEntity(assetEntity, asset.getScopes());
        return assetEntity;
    }

    public void setAdditionalScopeToAssetEntity(AssetEntity assetEntity, List<AssetAdditionalScope> assetAdditionalScope) {
        if (assetAdditionalScope == null) {
            return;
        }
//        assetAdditionalScope.stream()
//                .map(additionalScopeMapper::assetAdditionalScopeToEntity)
//                .forEach(assetEntity::addAdditionalScopeEntity);

        for (AssetAdditionalScope additionalScope : assetAdditionalScope) {
            assetEntity.addAdditionalScopeEntity(additionalScopeMapper.assetAdditionalScopeToEntity(additionalScope));
        }
    }


    public void setAdditionalScopeToContractEntity(ContractEntity contractEntity, List<ContractAdditionalScope> contractAdditionalScope) {
        if (contractAdditionalScope == null) {
            return;
        }
        contractAdditionalScope.stream().map(additionalScopeMapper::contractAdditionalScopeToEntity).forEach(contractEntity::addAdditionalScopeEntity);
    }

    public void setGuaranteesToAssetEntity(AssetEntity assetEntity, List<Guarantee> guarantees) {
        if (guarantees == null) {
            return;
        }
        guarantees.stream().map(guaranteeMapper::toEntity).forEach(assetEntity::addGuaranteeEntity);
    }

    public void setScopesToAssetEntity(AssetEntity assetEntity, List<Scope> scopes) {
        if (scopes == null) {
            return;
        }
        scopes.stream().map(scopeMapper::toEntity).forEach(assetEntity::addScopeEntity);
    }

    public Page<Contract> getAllContract(Pageable pageable) {
        return contractRepository.findAll(pageable).map(contractMapper::toResponse);
    }
}
