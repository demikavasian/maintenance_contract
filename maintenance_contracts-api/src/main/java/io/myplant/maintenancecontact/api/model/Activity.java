package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.myplant.maintenancecontact.api.model.enums.ActivityCategory;
import io.myplant.maintenancecontact.api.model.enums.ActivityService;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonRootName("activity")
public class Activity extends ScopeValue {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("activityService")
    private ActivityService activityService;

    @JsonProperty("activityCategory")
    private ActivityCategory activityCategory;
}
