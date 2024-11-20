package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.model.ContractEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = AssetMapper.class)
public interface ContractMapper {

    @Mapping(
            source = "contractEntity.assetEntities",
            target = "assets",
            qualifiedByName = "assetEntitySetToAssetList")
    Contract toResponse(ContractEntity contractEntity);

    @Mapping(
            source = "contract.assets",
            target = "assetEntities",
            qualifiedByName = "assetListToAssetEntitySet")
    ContractEntity toEntity(Contract contract);

}
