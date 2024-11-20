package io.myplant.maintenancecontact.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.myplant.maintenancecontact.api.model.enums.EventCategory;
import io.myplant.maintenancecontact.api.model.enums.EventService;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonRootName("event")
public class Event extends ScopeValue {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("event_service")
    private EventService eventService;

    @JsonProperty("event_category")
    private EventCategory eventCategory;
}
