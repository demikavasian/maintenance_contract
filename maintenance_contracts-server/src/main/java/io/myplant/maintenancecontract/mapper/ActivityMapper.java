package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Activity;
import io.myplant.maintenancecontract.model.ActivityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Named("activityEntityToActivity")
    Activity toResponse(ActivityEntity entity);

    @Named("activityToActivityEntity")
    ActivityEntity toEntity(Activity activity);
}
