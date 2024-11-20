package io.myplant.maintenancecontact.api.validation;

import io.myplant.maintenancecontact.api.model.Scope;
import io.myplant.maintenancecontact.api.model.ScopeValue;
import io.myplant.maintenancecontact.api.model.enums.ScopeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScopeValidator
        implements ConstraintValidator<ValidScope, Scope> {

    private static final String MISSING_SCOPE_TYPE = "Scope type is required";
    private static final String MISSING_SCOPE_VALUE = "Scope value is required";
    private static final String INVALID_SCOPE_VALUE = "Scope value is not valid for the given scope type";

    @Override
    public boolean isValid(Scope value, ConstraintValidatorContext context) {
        var isValid = true;

        if (value.getScopeType() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_SCOPE_TYPE)
                    .addPropertyNode("scopeType")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getScopeValue() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_SCOPE_VALUE)
                    .addPropertyNode("scopeValue")
                    .addConstraintViolation();
            isValid = false;
        }

        ScopeType scopeType = value.getScopeType();
        ScopeValue scopeValue = value.getScopeValue();

        Class<? extends ScopeValue> expectedClass = scopeType.getTypeClass();
        if (!expectedClass.isInstance(scopeValue)) {
            context.buildConstraintViolationWithTemplate(INVALID_SCOPE_VALUE)
                    .addPropertyNode("scopeValue")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
