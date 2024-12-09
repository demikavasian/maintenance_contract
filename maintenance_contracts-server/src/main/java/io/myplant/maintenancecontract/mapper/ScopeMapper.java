package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Scope;
import io.myplant.maintenancecontract.model.ScopeEntity;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

public interface ScopeMapper {

    @Named("scopeEntityToScope")
    Scope toResponse(ScopeEntity scopeEntity);

    @Named("scopeToScopeEntity")
    ScopeEntity toEntity(Scope scope);

    @Named("scopeEntitySetToScopeList")
    List<Scope> scopeEntitySetToScopeList (Set<ScopeEntity> scopeEntitySet);

}
