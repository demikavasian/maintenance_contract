package io.myplant.maintenancecontact.api.validation;

import io.myplant.maintenancecontact.api.model.Contract;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContractValidator
        implements ConstraintValidator<ValidContract, Contract> {

    private static final String MISSING_STATUS = "Status is required!";
    private static final String MISSING_OFFERING_TYPE = "OfferingType is required!";
    private static final String MISSING_NAME = "Name is required!";
    private static final String MISSING_EFFECTIVE_CONTRACT_START_DATE = "EffectiveContractStartDate is required!";
    private static final String MISSING_EXCLUSIVE_END_DATE = "ExclusiveEndDate is required!";
    private static final String MISSING_ASSETS = "Assets are required!";

    @Override
    public boolean isValid(Contract value, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (value.getStatus() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_STATUS)
                    .addPropertyNode("status")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getOfferingType() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_OFFERING_TYPE)
                    .addPropertyNode("offeringType")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getName() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_NAME)
                    .addPropertyNode("name")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getEffectiveContractStartDate() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_EFFECTIVE_CONTRACT_START_DATE)
                    .addPropertyNode("effectiveContractStartDate")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getExclusiveEndDate() == null) {
            context.buildConstraintViolationWithTemplate(MISSING_EXCLUSIVE_END_DATE)
                    .addPropertyNode("exclusiveEndDate")
                    .addConstraintViolation();
            isValid = false;
        }

        if (value.getAssets() == null || value.getAssets().isEmpty()) {
            context.buildConstraintViolationWithTemplate(MISSING_ASSETS)
                    .addPropertyNode("asset")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
