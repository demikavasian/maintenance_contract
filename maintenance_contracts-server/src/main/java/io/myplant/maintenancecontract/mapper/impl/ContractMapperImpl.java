package io.myplant.maintenancecontract.mapper.impl;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.mapper.AdditionalScopeMapper;
import io.myplant.maintenancecontract.mapper.AssetMapper;
import io.myplant.maintenancecontract.mapper.ContractMapper;
import io.myplant.maintenancecontract.model.ContractEntity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ContractMapperImpl implements ContractMapper {

    private AssetMapper assetMapper;
    private AdditionalScopeMapper additionalScopeMapper;

    @Override
    public Contract toResponse(ContractEntity contractEntity) {
        if ( contractEntity == null ) {
            return null;
        }

        Contract contract = new Contract();

        contract.setAssets( assetMapper.assetEntitySetToAssetList( contractEntity.getAssetEntities() ) );
        contract.setContractAdditionalScope( additionalScopeMapper.entitySetToContractAdditionScopeList( contractEntity.getAdditionalScopeEntities() ) );
        contract.setId( contractEntity.getId() );
        contract.setName( contractEntity.getName() );
        contract.setStatus( contractEntity.getStatus() );
        contract.setOfferingType( contractEntity.getOfferingType() );
        contract.setEffectiveContractStartDate( contractEntity.getEffectiveContractStartDate() );
        contract.setExclusiveEndDate( contractEntity.getExclusiveEndDate() );

        return contract;
    }

    @Override
    public ContractEntity toEntity(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractEntity contractEntity = new ContractEntity();

        contractEntity.setId( contract.getId() );
        contractEntity.setName( contract.getName() );
        contractEntity.setStatus( contract.getStatus() );
        contractEntity.setOfferingType( contract.getOfferingType() );
        contractEntity.setEffectiveContractStartDate( contract.getEffectiveContractStartDate() );
        contractEntity.setExclusiveEndDate( contract.getExclusiveEndDate() );

        return contractEntity;
    }
}
