package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Guarantee;
import io.myplant.maintenancecontract.model.GuaranteeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GuaranteeMapper {

    Guarantee toResponse(GuaranteeEntity entity);

    GuaranteeEntity toEntity(Guarantee guarantee);

    @Named("guaranteeEntitySetToGuaranteeList")
    List<Guarantee> guaranteeEntitySetToGuaranteeList(Set<GuaranteeEntity> entities);

    @Named("guaranteeListToGuaranteeEntitySet")
    Set<GuaranteeEntity> guaranteeListToGuaranteeEntitySet(List<Guarantee> guarantees);

}
