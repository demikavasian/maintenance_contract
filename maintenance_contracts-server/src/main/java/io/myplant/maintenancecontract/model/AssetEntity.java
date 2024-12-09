package io.myplant.maintenancecontract.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asset")
@EqualsAndHashCode(exclude = "contractEntity")
@ToString(exclude = "contractEntity")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Embedded
    private ClauseEntity clauseEntity;

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GuaranteeEntity> guaranteeEntities = new HashSet<>();

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScopeEntity> scopeEntities = new HashSet<>();

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdditionalScopeEntity> additionalScopeEntities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private ContractEntity contractEntity;

    public void addGuaranteeEntity(GuaranteeEntity guaranteeEntity) {
        guaranteeEntities.add(guaranteeEntity);
        guaranteeEntity.setAssetEntity(this);
    }

    public void removeGuaranteeEntity(GuaranteeEntity guaranteeEntity) {
        guaranteeEntities.remove(guaranteeEntity);
        guaranteeEntity.setAssetEntity(null);
    }

    public void addScopeEntity(ScopeEntity scopeEntity) {
        scopeEntities.add(scopeEntity);
        scopeEntity.setAssetEntity(this);
    }

    public void removeScopeEntity(ScopeEntity scopeEntity) {
        scopeEntities.remove(scopeEntity);
        scopeEntity.setAssetEntity(null);
    }

    public void addAdditionalScopeEntity(AdditionalScopeEntity additionalScopeEntity) {
        additionalScopeEntities.add(additionalScopeEntity);
        additionalScopeEntity.setAssetEntity(this);
    }

    public void removeAdditionalScopeEntity(AdditionalScopeEntity additionalScopeEntity) {
        additionalScopeEntities.remove(additionalScopeEntity);
        additionalScopeEntity.setAssetEntity(null);
    }
}
