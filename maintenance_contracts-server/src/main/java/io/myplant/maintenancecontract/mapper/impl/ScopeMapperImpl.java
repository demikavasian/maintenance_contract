package io.myplant.maintenancecontract.mapper.impl;

import io.myplant.maintenancecontact.api.model.*;
import io.myplant.maintenancecontract.mapper.ScopeMapper;
import io.myplant.maintenancecontract.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class ScopeMapperImpl implements ScopeMapper {

    @Override
    public Scope toResponse(ScopeEntity scopeEntity) {
        if ( scopeEntity == null ) {
            return null;
        }

        Scope scope = new Scope();

        scope.setId( scopeEntity.getId() );
        scope.setScopeType( scopeEntity.getScopeType() );
        scope.setScopeCoverage( scopeEntity.getScopeCoverage() );
        scope.setAdditionalAttribute( scopeEntity.getAdditionalAttribute() );
        scope.setAdditionalInfo( scopeEntity.getAdditionalInfo() );
        scope.setScopeValue( toScopeValue( scopeEntity ) );

        return scope;
    }

    @Override
    public ScopeEntity toEntity(Scope scope) {
        if ( scope == null ) {
            return null;
        }

        ScopeEntity scopeEntity = new ScopeEntity();

        scopeEntity.setId( scope.getId() );
        scopeEntity.setScopeType( scope.getScopeType() );
        scopeEntity.setScopeCoverage( scope.getScopeCoverage() );
        scopeEntity.setAdditionalAttribute( scope.getAdditionalAttribute() );
        scopeEntity.setAdditionalInfo( scope.getAdditionalInfo() );
        assignScopeValueToEntity( scopeEntity, scope.getScopeValue() );

        return scopeEntity;
    }

    protected void assignScopeValueToEntity(ScopeEntity scopeEntity, ScopeValue scopeValue) {
        if ( scopeValue == null ) {
            return;
        }

        if ( scopeValue instanceof Activity ) {
            scopeEntity.setActivityEntity( activityToActivityEntity( (Activity) scopeValue ) );
        }
        else if ( scopeValue instanceof Event ) {
            scopeEntity.setEventEntity( eventToEventEntity( (Event) scopeValue ) );
        }
        else if ( scopeValue instanceof io.myplant.maintenancecontact.api.model.Component ) {
            scopeEntity.setComponentEntity( componentToComponentEntity( (io.myplant.maintenancecontact.api.model.Component) scopeValue ) );
        }
    }

    protected ActivityEntity activityToActivityEntity(Activity scopeValue) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId( scopeValue.getId() );
        activityEntity.setActivityService( scopeValue.getActivityService() );
        activityEntity.setActivityCategory( scopeValue.getActivityCategory() );
        return activityEntity;
    }

    protected EventEntity eventToEventEntity(Event scopeValue) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId( scopeValue.getId() );
        eventEntity.setEventService( scopeValue.getEventService() );
        eventEntity.setEventCategory( scopeValue.getEventCategory() );
        return eventEntity;
    }

    protected ComponentEntity componentToComponentEntity(io.myplant.maintenancecontact.api.model.Component scopeValue) {
        ComponentEntity componentEntity = new ComponentEntity();
        componentEntity.setId( scopeValue.getId() );
        componentEntity.setComponentName( scopeValue.getComponentName() );
        componentEntity.setComponentNodeId( scopeValue.getComponentNodeId() );
        return componentEntity;
    }

    protected ScopeValue toScopeValue(ScopeEntity scopeEntity) {
        return switch (scopeEntity.getScopeType()) {
            case ACTIVITY -> getScopeValueFromActivityEntity(scopeEntity.getActivityEntity());
            case EVENT -> getScopeValueFromEventEntity(scopeEntity.getEventEntity());
            case COMPONENT -> getScopeValueFromComponentEntity(scopeEntity.getComponentEntity());
        };

    }

    protected ScopeValue getScopeValueFromActivityEntity(ActivityEntity activityEntity) {
        if(activityEntity == null){
            return null;
        }
        Activity activity = new Activity();
        activity.setId(activityEntity.getId());
        activity.setActivityService(activityEntity.getActivityService());
        activity.setActivityCategory(activityEntity.getActivityCategory());
        return activity;
    }

    protected ScopeValue getScopeValueFromEventEntity(EventEntity eventEntity) {
        if(eventEntity == null){
            return null;
        }
        Event event = new Event();
        event.setId(eventEntity.getId());
        event.setEventService(eventEntity.getEventService());
        event.setEventCategory(eventEntity.getEventCategory());
        return event;
    }

    protected ScopeValue getScopeValueFromComponentEntity(ComponentEntity componentEntity) {
        if(componentEntity == null){
            return null;
        }
        io.myplant.maintenancecontact.api.model.Component component = new io.myplant.maintenancecontact.api.model.Component();
        component.setId(componentEntity.getId());
        component.setComponentName(componentEntity.getComponentName());
        component.setComponentNodeId(componentEntity.getComponentNodeId());
        return component;
    }

    @Override
    public List<Scope> scopeEntitySetToScopeList(Set<ScopeEntity> scope) {
        if ( scope == null ) {
            return null;
        }

        List<Scope> list = new ArrayList<>( scope.size() );
        for ( ScopeEntity scopeEntity : scope ) {
            list.add( toResponse( scopeEntity ) );
        }

        return list;
    }
}
