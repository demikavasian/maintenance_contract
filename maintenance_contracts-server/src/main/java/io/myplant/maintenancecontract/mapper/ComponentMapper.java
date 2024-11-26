package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Component;
import io.myplant.maintenancecontract.model.ComponentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    @Named("componentEntityToComponent")
    Component toResponse(ComponentEntity entity);

    @Named("componentToComponentEntity")
    ComponentEntity toEntity(Component component);
}
