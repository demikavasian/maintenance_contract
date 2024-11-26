package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.model.ContractEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", uses = {AssetMapper.class, AdditionalScopeMapper.class})
public interface ContractMapper {

    @Mapping(
            source = "contractEntity.assetEntities",
            target = "assets",
            qualifiedByName = "assetEntitySetToAssetList")
    @Mapping(source = "contractEntity.additionalScopeEntities",
            target = "contractAdditionalScope",
            qualifiedByName = "entitySetToContractAdditionScopeList")
    Contract toResponse(ContractEntity contractEntity);

    @Named("contractToContractEntity")
    ContractEntity toEntity(Contract contract);

}
