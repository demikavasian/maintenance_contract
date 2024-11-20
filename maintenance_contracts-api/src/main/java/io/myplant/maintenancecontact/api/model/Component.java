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

    @JsonProperty("component_node_id")
    private Long componentNodeId;

    @JsonProperty("component_name")
    private String componentName;
}
