package io.myplant.maintenancecontract.mapper.impl;

import io.myplant.maintenancecontact.api.model.AssetAdditionalScope;
import io.myplant.maintenancecontact.api.model.ContractAdditionalScope;
import io.myplant.maintenancecontract.mapper.AdditionalScopeMapper;
import io.myplant.maintenancecontract.model.AdditionalScopeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AdditionalScopeImpl implements AdditionalScopeMapper{
    @Override
    public AdditionalScopeEntity assetAdditionalScopeToEntity(AssetAdditionalScope assetAdditionalScope) {
        AdditionalScopeEntity additionalScopeEntity = new AdditionalScopeEntity();
        additionalScopeEntity.setService(assetAdditionalScope.getService());
        return additionalScopeEntity;
    }

    @Override
    public AdditionalScopeEntity contractAdditionalScopeToEntity(ContractAdditionalScope contractAdditionalScope) {
        AdditionalScopeEntity additionalScopeEntity = new AdditionalScopeEntity();
        additionalScopeEntity.setService(contractAdditionalScope.getService());
        return additionalScopeEntity;
    }

    @Override
    public AssetAdditionalScope entityToAssetAdditionalScope(AdditionalScopeEntity additionalScopeEntity) {
        AssetAdditionalScope assetAdditionalScope = new AssetAdditionalScope();
        assetAdditionalScope.setService(additionalScopeEntity.getService());
        return assetAdditionalScope;
    }

    @Override
    public ContractAdditionalScope entityToContractAdditionalScope(AdditionalScopeEntity additionalScopeEntity) {
        ContractAdditionalScope contractAdditionalScope = new ContractAdditionalScope();
        contractAdditionalScope.setService(additionalScopeEntity.getService());
        return contractAdditionalScope;
    }

    @Override
    public List<AssetAdditionalScope> entitySetToAdditionScopeList(Set<AdditionalScopeEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AssetAdditionalScope> list = new ArrayList<AssetAdditionalScope>( entities.size() );
        for ( AdditionalScopeEntity additionalScopeEntity : entities ) {
            list.add( entityToAssetAdditionalScope( additionalScopeEntity ) );
        }

        return list;
    }


    @Override
    public List<ContractAdditionalScope> entitySetToContractAdditionScopeList(Set<AdditionalScopeEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ContractAdditionalScope> list = new ArrayList<ContractAdditionalScope>( entities.size() );
        for ( AdditionalScopeEntity contractScopeEntity : entities ) {
            list.add( entityToContractAdditionalScope( contractScopeEntity ) );
        }

        return list;
    }


}
