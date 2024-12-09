package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Asset;
import io.myplant.maintenancecontract.model.AssetEntity;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

public interface AssetMapper {

    Asset toResponse(AssetEntity assetEntity);

    @Named("assetToAssetEntity")
    AssetEntity toEntity(Asset asset);

    @Named("assetEntitySetToAssetList")
    List<Asset> assetEntitySetToAssetList(Set<AssetEntity> set);


}
