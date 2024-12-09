package io.myplant.maintenancecontract.mapper;

import io.myplant.maintenancecontact.api.model.Contract;
import io.myplant.maintenancecontract.model.ContractEntity;
import org.mapstruct.Named;


public interface ContractMapper {

    Contract toResponse(ContractEntity contractEntity);

    @Named("contractToContractEntity")
    ContractEntity toEntity(Contract contract);

}
