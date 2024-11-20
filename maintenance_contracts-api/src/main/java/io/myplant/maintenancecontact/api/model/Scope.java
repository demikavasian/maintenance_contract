package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.myplant.maintenancecontact.api.model.enums.ScopeCoverage;
import io.myplant.maintenancecontact.api.model.enums.ScopeType;
import io.myplant.maintenancecontact.api.validation.ValidScope;
import lombok.Data;

@Data
@JsonRootName("scope")
@JsonDeserialize(using = io.myplant.maintenancecontact.api.util.ScopeDeserializer.class)
@ValidScope
public class Scope{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("scope_type")
    private ScopeType scopeType;

    @JsonProperty("scope_value")
    private ScopeValue scopeValue;

    @JsonProperty("scope_coverage")
    private ScopeCoverage scopeCoverage;

    @JsonProperty("additional_attribute")
    private String additionalAttribute;

    @JsonProperty("additional_info")
    private String additionalInfo;

}
