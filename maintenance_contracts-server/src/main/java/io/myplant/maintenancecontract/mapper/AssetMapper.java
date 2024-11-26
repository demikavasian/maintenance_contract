package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Asset;
import io.myplant.maintenancecontract.model.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ClauseMapper.class, GuaranteeMapper.class, ScopeMapper.class, AdditionalScopeMapper.class})
public interface AssetMapper {

    @Mapping(
            source = "assetEntity.clauseEntity",
            target = "clause",
            qualifiedByName = "clauseEntityToClause")
    @Mapping(
            source = "assetEntity.guaranteeEntities",
            target = "guarantees",
            qualifiedByName = "guaranteeEntitySetToGuaranteeList"
    )
    @Mapping(
            source = "assetEntity.scopeEntities",
            target = "scopes",
            qualifiedByName = "scopeEntitySetToScopeList"
    )
    @Mapping(source = "assetEntity.additionalScopeEntities",
            target = "assetAdditionalScope",
            qualifiedByName = "entitySetToAdditionScopeList")
    Asset toResponse(AssetEntity assetEntity);

    @Mapping(
            source = "asset.clause",
            target = "clauseEntity",
            qualifiedByName = "clauseToClauseEntity")
    @Named("assetToAssetEntity")
    AssetEntity toEntity(Asset asset);

    @Named("assetEntitySetToAssetList")
    List<Asset> assetEntitySetToAssetList(Set<AssetEntity> set);


}
