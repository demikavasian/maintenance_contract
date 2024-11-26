package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("contractAdditionalScope")
public class ContractAdditionalScope {

    @JsonProperty("service")
    private String service;
}

