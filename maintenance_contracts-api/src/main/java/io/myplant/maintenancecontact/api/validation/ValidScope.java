package io.myplant.maintenancecontact.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, TYPE_USE, METHOD, PARAMETER})@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScopeValidator.class)
@Documented
public @interface ValidScope {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
