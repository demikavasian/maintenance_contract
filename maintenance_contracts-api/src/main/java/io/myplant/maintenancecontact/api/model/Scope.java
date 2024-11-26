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

    @JsonProperty("scopeType")
    private ScopeType scopeType;

    @JsonProperty("scopeValue")
    private ScopeValue scopeValue;

    @JsonProperty("scopeCoverage")
    private ScopeCoverage scopeCoverage;

    @JsonProperty("additionalAttribute")
    private String additionalAttribute;

    @JsonProperty("additionalInfo")
    private String additionalInfo;

}
