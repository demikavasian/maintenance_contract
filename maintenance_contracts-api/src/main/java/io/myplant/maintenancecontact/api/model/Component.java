package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonRootName("component")
public class Component extends ScopeValue {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("componentNodeId")
    private Long componentNodeId;

    @JsonProperty("componentName")
    private String componentName;
}
