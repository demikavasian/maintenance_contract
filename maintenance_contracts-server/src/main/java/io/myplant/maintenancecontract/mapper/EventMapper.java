package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Event;
import io.myplant.maintenancecontract.model.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Named("eventEntityToEvent")
    Event toResponse(EventEntity entity);

    @Named("eventToEventEntity")
    EventEntity toEntity(Event event);
}
