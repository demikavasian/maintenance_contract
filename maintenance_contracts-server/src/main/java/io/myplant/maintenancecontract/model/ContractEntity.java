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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Set<AdditionalScopeEntity> additionalScopeEntities = new HashSet<>();

    @OneToMany(mappedBy = "contractEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AssetEntity> assetEntities = new HashSet<>();


    public void addAssetEntity(AssetEntity assetEntity) {
        assetEntities.add(assetEntity);
        assetEntity.setContractEntity(this);
    }

    public void removeAssetEntity(AssetEntity assetEntity) {
        assetEntities.remove(assetEntity);
        assetEntity.setContractEntity(null);
    }

    public void addAdditionalScopeEntity(AdditionalScopeEntity additionalScopeEntity) {
        additionalScopeEntities.add(additionalScopeEntity);
        additionalScopeEntity.setContractEntity(this);
    }

    public void removeAdditionalScopeEntity(AdditionalScopeEntity additionalScopeEntity) {
        additionalScopeEntities.remove(additionalScopeEntity);
        additionalScopeEntity.setContractEntity(null);
    }

}
