package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Clause;
import io.myplant.maintenancecontract.model.ClauseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClauseMapper {

    @Named("clauseEntityToClause")
    Clause toResponse(ClauseEntity entity);

    @Named("clauseToClauseEntity")
    ClauseEntity toEntity(Clause clause);

}
