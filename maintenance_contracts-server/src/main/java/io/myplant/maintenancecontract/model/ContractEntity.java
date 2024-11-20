package io.myplant.maintenancecontract.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.myplant.maintenancecontact.api.model.enums.ContractStatus;
import io.myplant.maintenancecontact.api.model.enums.OfferingType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class ContractEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Enumerated(EnumType.STRING)
    private OfferingType offeringType;

    @Column(name = "effective_contract_start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveContractStartDate;

    @Column(name = "exclusive_end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate exclusiveEndDate;

    @OneToMany(mappedBy = "contractEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AssetEntity> assetEntities = new HashSet<>();


}
