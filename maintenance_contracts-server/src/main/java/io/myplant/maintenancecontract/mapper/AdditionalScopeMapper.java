package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.AssetAdditionalScope;
import io.myplant.maintenancecontact.api.model.ContractAdditionalScope;
import io.myplant.maintenancecontract.model.AdditionalScopeEntity;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

public interface AdditionalScopeMapper {

    @Named("assetAdditionalScopeToEntity")
    AdditionalScopeEntity assetAdditionalScopeToEntity(AssetAdditionalScope assetAdditionalScope);

    @Named("contractAdditionalScopeToEntity")
    AdditionalScopeEntity contractAdditionalScopeToEntity(ContractAdditionalScope contractAdditionalScope);

    @Named("entityToAssetAdditionalScope")
    AssetAdditionalScope entityToAssetAdditionalScope(AdditionalScopeEntity additionalScopeEntity);

    @Named("entityToContractAdditionalScope")
    ContractAdditionalScope entityToContractAdditionalScope(AdditionalScopeEntity additionalScopeEntity);

    @Named("entitySetToAdditionScopeList")
    List<AssetAdditionalScope> entitySetToAdditionScopeList(Set<AdditionalScopeEntity> additionalScopeEntities);

    @Named("entitySetToContractAdditionScopeList")
    List<ContractAdditionalScope> entitySetToContractAdditionScopeList(Set<AdditionalScopeEntity> additionalScopeEntities);
}
