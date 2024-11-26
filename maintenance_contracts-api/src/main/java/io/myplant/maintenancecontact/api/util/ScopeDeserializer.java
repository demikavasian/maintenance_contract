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

        JsonNode scopeTypeNode = node.get("scopeType");
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

        JsonNode scopeCoverageNode = node.get("scopeCoverage");
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

        JsonNode scopeValueNode = node.get("scopeValue");
        if (scopeValueNode == null || scopeValueNode.isNull()) {
            throw new JsonMappingException(p, "Missing 'scopeValue' field");
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
                throw new JsonMappingException(p, "Unsupported 'scopeType': " + scopeTypeStr);
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
        if (activity.getActivityService() == null) {
            throw new JsonMappingException(p, "'activityService' is required for ACTIVITY scopeType");
        }
        if (activity.getActivityCategory() == null) {
            throw new JsonMappingException(p, "'activityCategory' is required for ACTIVITY scopeType");
        }

        validateNoExtraFields(node, new String[]{"id", "activityService", "activityCategory"}, p);
    }

    private void validateEvent(JsonNode node, Event event, JsonParser p) throws JsonMappingException {
        if (event.getEventService() == null) {
            throw new JsonMappingException(p, "'eventService' is required for EVENT scopeType");
        }
        if (event.getEventCategory() == null) {
            throw new JsonMappingException(p, "'eventCategory' is required for EVENT scopeType");
        }

        validateNoExtraFields(node, new String[]{"id", "eventService", "eventCategory"}, p);
    }

    private void validateComponent(JsonNode node, Component component, JsonParser p) throws JsonMappingException {
        if (component.getComponentNodeId() == null) {
            throw new JsonMappingException(p, "'componentNodeId' is required for COMPONENT scopeType");
        }
        if (component.getComponentName() == null || component.getComponentName().isEmpty()) {
            throw new JsonMappingException(p, "'componentName' is required for COMPONENT scopeType");
        }

        validateNoExtraFields(node, new String[]{"id", "componentNodeId", "componentName"}, p);
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
                throw new JsonMappingException(p, "Unexpected field '" + field + "' in scopeValue");
            }
        }
    }
}
