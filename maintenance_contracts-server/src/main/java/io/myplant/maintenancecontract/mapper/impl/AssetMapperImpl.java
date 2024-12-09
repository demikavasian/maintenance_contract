package io.myplant.maintenancecontract.mapper.impl;

import io.myplant.maintenancecontact.api.model.Asset;
import io.myplant.maintenancecontract.mapper.*;
import io.myplant.maintenancecontract.model.AssetEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AssetMapperImpl implements AssetMapper {


    private ClauseMapper clauseMapper;
    private GuaranteeMapper guaranteeMapper;
    private ScopeMapper scopeMapper;
    private AdditionalScopeMapper additionalScopeMapper;

    @Override
    public Asset toResponse(AssetEntity assetEntity) {
        if ( assetEntity == null ) {
            return null;
        }

        Asset asset = new Asset();

        asset.setClause( clauseMapper.toResponse( assetEntity.getClauseEntity() ) );
        asset.setGuarantees( guaranteeMapper.guaranteeEntitySetToGuaranteeList( assetEntity.getGuaranteeEntities() ) );
        asset.setScopes( scopeMapper.scopeEntitySetToScopeList( assetEntity.getScopeEntities() ) );
        asset.setAssetAdditionalScope( additionalScopeMapper.entitySetToAdditionScopeList( assetEntity.getAdditionalScopeEntities() ) );
        asset.setId( assetEntity.getId() );
        asset.setAssetId( assetEntity.getAssetId() );

        return asset;
    }

    @Override
    public AssetEntity toEntity(Asset asset) {
        if ( asset == null ) {
            return null;
        }

        AssetEntity assetEntity = new AssetEntity();

        assetEntity.setClauseEntity( clauseMapper.toEntity( asset.getClause() ) );
        assetEntity.setId( asset.getId() );
        assetEntity.setAssetId( asset.getAssetId() );

        return assetEntity;
    }

    @Override
    public List<Asset> assetEntitySetToAssetList(Set<AssetEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<Asset> list = new ArrayList<>( set.size() );
        for ( AssetEntity assetEntity : set ) {
            list.add( toResponse( assetEntity ) );
        }

        return list;
    }
}
