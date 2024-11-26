package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonRootName("assetAdditionalScope")
public class AssetAdditionalScope {

    @JsonProperty("service")
    private String service;
}
