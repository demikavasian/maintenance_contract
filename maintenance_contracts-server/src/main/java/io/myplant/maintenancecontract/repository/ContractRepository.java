package io.myplant.maintenancecontract.repository;

import io.myplant.maintenancecontract.model.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

}
