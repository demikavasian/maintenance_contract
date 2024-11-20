package io.myplant.maintenancecontact.api.model.enums;


import io.myplant.maintenancecontact.api.model.Activity;
import io.myplant.maintenancecontact.api.model.Component;
import io.myplant.maintenancecontact.api.model.Event;
import io.myplant.maintenancecontact.api.model.ScopeValue;

public enum ScopeType {
    EVENT(Event.class),
    ACTIVITY(Activity.class),
    COMPONENT(Component.class);

    private final Class<? extends ScopeValue> clazz;

    ScopeType(Class<? extends ScopeValue> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ScopeValue> getTypeClass() {
        return clazz;
    }
}
