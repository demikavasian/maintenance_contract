package io.myplant.maintenancecontact.api.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import io.myplant.maintenancecontact.api.model.*;
import io.myplant.maintenancecontact.api.model.enums.ScopeCoverage;
import io.myplant.maintenancecontact.api.model.enums.ScopeType;

import java.io.IOException;
import java.util.Iterator;

public class ScopeDeserializer extends JsonDeserializer<Scope> {
    @Override
    public Scope deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);

        JsonNode scopeTypeNode = node.get("scope_type");
        if(scopeTypeNode == null || scopeTypeNode.isNull()) {
            throw new JsonMappingException(p, "scopeType is required");
        }
        String scopeTypeStr = scopeTypeNode.asText();
        ScopeType scopeType;
        try{
            scopeType = ScopeType.valueOf(scopeTypeStr);
        }catch (IllegalArgumentException e) {
            throw new JsonMappingException(p, "Invalid scopeType: " + scopeTypeStr);
        }

        JsonNode scopeCoverageNode = node.get("scope_coverage");
        if(scopeCoverageNode == null || scopeCoverageNode.isNull()) {
            throw new JsonMappingException(p, "scopeCoverage is required");
        }
        String scopeCoverageStr = scopeCoverageNode.asText();
        ScopeCoverage scopeCoverage;
        try {
            scopeCoverage = ScopeCoverage.valueOf(scopeCoverageStr);
        }catch (IllegalArgumentException e) {
            throw new JsonMappingException(p, "Invalid scopeCoverage: " + scopeCoverageStr);
        }

        JsonNode scopeValueNode = node.get("scope_value");
        if (scopeValueNode == null || scopeValueNode.isNull()) {
            throw new JsonMappingException(p, "Missing 'scope_value' field");
        }
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ScopeValue scopeValue;

        switch (scopeType) {
            case ACTIVITY:
                scopeValue = mapper.treeToValue(scopeValueNode, Activity.class);
                validateActivity(scopeValueNode, (Activity) scopeValue, p);
                break;
            case EVENT:
                scopeValue = mapper.treeToValue(scopeValueNode, Event.class);
                validateEvent(scopeValueNode, (Event) scopeValue, p);
                break;
            case COMPONENT:
                scopeValue = mapper.treeToValue(scopeValueNode, Component.class);
                validateComponent(scopeValueNode, (Component) scopeValue, p);
                break;
            default:
                throw new JsonMappingException(p, "Unsupported 'scope_type': " + scopeTypeStr);
        }

        Scope scope = new Scope();
        scope.setId(node.has("id") && !node.get("id").isNull() ? node.get("id").asLong() : null);
        scope.setScopeType(scopeType);
        scope.setScopeCoverage(scopeCoverage);
        scope.setAdditionalAttribute(node.has("additionalAttribute") && !node.get("additionalAttribute").isNull() ? node.get("additionalAttribute").asText() : null);
        scope.setAdditionalInfo(node.has("additionalInfo") && !node.get("additionalInfo").isNull() ? node.get("additionalInfo").asText() : null);
        scope.setScopeValue(scopeValue);

        return scope;
    }

    private void validateActivity(JsonNode node, Activity activity, JsonParser p) throws JsonMappingException {
        if (activity.getId() == null) {
            throw new JsonMappingException(p, "'activity_service' is required for ACTIVITY scope_type");
        }
        if (activity.getActivityService() == null) {
            throw new JsonMappingException(p, "'activity_service' is required for ACTIVITY scope_type");
        }
        if (activity.getActivityCategory() == null) {
            throw new JsonMappingException(p, "'activity_category' is required for ACTIVITY scope_type");
        }

        validateNoExtraFields(node, new String[]{"id", "activity_service", "activity_category"}, p);
    }

    private void validateEvent(JsonNode node, Event event, JsonParser p) throws JsonMappingException {
        if (event.getEventService() == null) {
            throw new JsonMappingException(p, "'event_service' is required for EVENT scope_type");
        }
        if (event.getEventCategory() == null) {
            throw new JsonMappingException(p, "'event_category' is required for EVENT scope_type");
        }

        validateNoExtraFields(node, new String[]{"id", "event_service", "event_category"}, p);
    }

    private void validateComponent(JsonNode node, Component component, JsonParser p) throws JsonMappingException {
        if (component.getComponentNodeId() == null) {
            throw new JsonMappingException(p, "'component_node_id' is required for COMPONENT scope_type");
        }
        if (component.getComponentName() == null || component.getComponentName().isEmpty()) {
            throw new JsonMappingException(p, "'component_name' is required for COMPONENT scope_type");
        }

        validateNoExtraFields(node, new String[]{"id", "component_node_id", "component_name"}, p);
    }

    private void validateNoExtraFields(JsonNode node, String[] expectedFields, JsonParser p) throws JsonMappingException {
        for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
            String field = it.next();
            boolean expected = false;
            for (String expectedField : expectedFields) {
                if (expectedField.equals(field)) {
                    expected = true;
                    break;
                }
            }
            if (!expected) {
                throw new JsonMappingException(p, "Unexpected field '" + field + "' in scope_value");
            }
        }
    }
}
